package team.gt.admin.application.parser.reservation

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.domain.reservation.Reservation
import team.gt.admin.application.domain.reservation.ReservationItem
import team.gt.admin.application.storage.entity.ReservationEntity
import team.gt.admin.application.storage.entity.ReservationItemEntity

object ReservationParser {

    fun fromDomain(domain: Reservation): ReservationEntity {
        val reservedVisitDateTime = quarterToTime(domain.reservedVisitDate, domain.reservedVisitHour, domain.reservedVisitQuarter)

        return ReservationEntity(
            id = if (domain.id == JUST_CREATED_ID) { null } else domain.id,
            staffId = domain.staffId,
            customerId = domain.customer.customerId,
            reservationSource = domain.customer.reservationSource,
            reservedVisitDateTime = reservedVisitDateTime,
            actualVisitDateTime = domain.actualVisitDateTime,
            status = domain.status,
            regTs = domain.regTs,
            updater = domain.updater,
            updTs = domain.updTs,
        )
    }

    fun fromDomain(domain: ReservationItem): ReservationItemEntity {

        return ReservationItemEntity(
            id = if (domain.id == JUST_CREATED_ID) { null } else domain.id,
            reservationId = domain.reservationId,
            itemType = domain.itemType,
            itemId = domain.itemId,
            itemName = domain.itemName,
            price = domain.price,
            quarterTaken = domain.quarterTaken,
            status = domain.status,
            regTs = domain.regTs,
            updater = domain.updater,
            updTs = domain.updTs,
        )
    }

    fun toDomain(entity: ReservationEntity, reservationItemEntities: List<ReservationItemEntity>): Reservation {
        val items = reservationItemEntities
            .map { itemEntity ->
                ReservationItem(
                    id = itemEntity.id!!,
                    reservationId = itemEntity.reservationId,
                    itemType = itemEntity.itemType,
                    itemId = itemEntity.itemId,
                    itemName = itemEntity.itemName,
                    price = itemEntity.price,
                    quarterTaken = itemEntity.quarterTaken,
                    status = itemEntity.status,
                    regTs = itemEntity.regTs,
                    updater = itemEntity.updater,
                    updTs = itemEntity.updTs
                )
            }

        return Reservation(
            id = entity.id!!,
            staffId = entity.staffId,
            customer = Reservation.Customer(
                entity.customerId,
                entity.reservationSource,
            ),
            reservedVisitDate = entity.reservedVisitDateTime.toLocalDate(),
            reservedVisitHour = entity.reservedVisitDateTime.hour,
            reservedVisitQuarter = entity.reservedVisitDateTime.minute * 15,
            actualVisitDateTime = entity.actualVisitDateTime,
            items = items,
            status = entity.status,
            regTs = entity.regTs,
            updater = entity.updater,
            updTs = entity.updTs,
        )
    }


    fun quarterToTime(date: LocalDate, hour: Int, quarter: Int): LocalDateTime {
        val minute = (quarter - 1) * 15
        return LocalDateTime.of(date, LocalTime.of(hour, minute))
    }
}