package team.gt.admin.application.domain.reservation

import java.time.LocalDate
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import team.gt.admin.application.domain.customer.CustomerReader
import team.gt.admin.application.domain.staff.Staff
import team.gt.admin.application.domain.staff.StaffReader
import team.gt.admin.application.exception.DomainNotFoundException
import team.gt.admin.application.parser.reservation.ReservationListView
import team.gt.admin.application.parser.reservation.ReservationParser
import team.gt.admin.application.storage.repository.ReservationItemRepository
import team.gt.admin.application.storage.repository.ReservationRepository

@Component
class ReservationReader(
    private val reservationRepository: ReservationRepository,
    private val reservationItemRepository: ReservationItemRepository,
    private val customerReader: CustomerReader,
    private val staffReader: StaffReader,
) {

    fun readByIdOrThrow(reservationId: Long): Reservation {
        val reservationEntity = reservationRepository.findById(reservationId).orElseThrow { throw DomainNotFoundException() }!!
        val reservationItemEntities = reservationItemRepository.findByReservationIdIn(listOf(reservationId))

        return ReservationParser.toDomain(
            entity = reservationEntity,
            reservationItemEntities = reservationItemEntities,
        )
    }

    fun search(
        staffId: Long?,
        startDate: LocalDate,
        endDate: LocalDate,
        size: Int,
        lastCursor: Long,
    ): List<ReservationListView> {
        // TODO. staffId
        val reservationEntities = reservationRepository.findByIdGreaterThanAndReservedVisitDateTimeBetween(
            cursor = lastCursor,
            searchStartDateTimeInclusive = startDate.atStartOfDay(),
            searchEndDateTimeInclusive = endDate.plusDays(1).atStartOfDay().minusNanos(1),
            page = PageRequest.ofSize(size),
        )

        val reservationIds = mutableSetOf<Long>()
        val customerIds = mutableSetOf<Long>()

        reservationEntities
            .forEach { entity ->
                reservationIds.add(entity.id!!)
                entity.customerId?.let { customerIds.add(it) }
            }

        val reservationIdReservationItemEntitiesMap = reservationIds
            .chunked(128)
            .flatMap { reservationIds -> reservationItemRepository.findByReservationIdIn(reservationIds) }
            .groupBy { it.reservationId }

        val staffIdStaffMap = staffReader
            .readAllWorkingStaffs()
            .associateBy(Staff::id)

        val customerIdCustomerMap = customerIds
            .chunked(128)
            .flatMap { customerReader.readCustomerAndPersonByCustomerIds(it) }
            .associateBy { it.customerId }

        return reservationEntities
            .map { reservationEntity ->
                ReservationListView(
                    reservationId = reservationEntity.id!!,
                    reservedVisitDateTime = reservationEntity.reservedVisitDateTime,
                    reservedHour = reservationEntity.reservedVisitDateTime.hour,
                    reservedQuarter = ReservationParser.timeToQuarter(reservationEntity.reservedVisitDateTime),
                    staffId = staffIdStaffMap[reservationEntity.staffId]!!.id,
                    staffNickname = staffIdStaffMap[reservationEntity.staffId]!!.nickname,
                    customerIdOrNull = reservationEntity.customerId,
                    customerNameOrNull = customerIdCustomerMap[reservationEntity.customerId]?.personName,
                    reservationSource = reservationEntity.reservationSource,
                    reservationStatus = reservationEntity.status,
                    reservationItems = reservationIdReservationItemEntitiesMap[reservationEntity.id]!!.map(ReservationParser::toDomain),
                    actualVisitDateTimeOrNull = reservationEntity.actualVisitDateTime,
                )
            }
    }
}