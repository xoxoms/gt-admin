package team.gt.admin.application.domain.reservation

import java.time.LocalDate
import org.springframework.stereotype.Component
import team.gt.admin.application.domain.staff.Staff
import team.gt.admin.application.domain.staff.StaffReader
import team.gt.admin.application.parser.reservation.ReservationListView
import team.gt.admin.application.parser.reservation.ReservationParser
import team.gt.admin.application.storage.repository.ReservationItemRepository
import team.gt.admin.application.storage.repository.ReservationRepository

@Component
class ReservationReader(
    private val reservationRepository: ReservationRepository,
    private val reservationItemRepository: ReservationItemRepository,
    private val staffReader: StaffReader,
) {

    fun search(
        staffId: Long?,
        date: LocalDate?,
        hour: Int?,
        size: Int,
        lastCursor: Long?,
    ): List<ReservationListView> {
        // TODO. 동적쿼리
        val reservationEntities = reservationRepository.findAll()
        val reservationIds = mutableSetOf<Long>()
        val customerIds = mutableSetOf<Long>()

        reservationEntities.forEach { entity ->
            reservationIds.add(entity.id!!)
            entity.customerId?.let { customerIds.add(it) }
        }

        val reservationIdReservationItemEntitiesMap =
            reservationIds
            .chunked(128)
            .flatMap { reservationIds -> reservationItemRepository.findByReservationIdIn(reservationIds) }
            .groupBy { it.reservationId }


        val staffIdStaffMap = staffReader
            .readAllWorkingStaffs()
            .associateBy(Staff::id)

        return reservationEntities
            .map { reservationEntity ->
                ReservationListView(
                    reservationId = reservationEntity.id!!,
                    reservedVisitDateTime = reservationEntity.reservedVisitDateTime,
                    staffId = staffIdStaffMap[reservationEntity.staffId]!!.id,
                    staffNickname = staffIdStaffMap[reservationEntity.staffId]!!.nickname,
                    customerIdOrNull = reservationEntity.customerId,
                    reservationItems = reservationIdReservationItemEntitiesMap[reservationEntity.id]!!.map(ReservationParser::toDomain),
                    reservationStatus = reservationEntity.status,
                    actualVisitDateTimeOrNull = reservationEntity.actualVisitDateTime,
                )
            }
    }
}