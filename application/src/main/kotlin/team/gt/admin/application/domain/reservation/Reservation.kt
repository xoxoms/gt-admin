package team.gt.admin.application.domain.reservation

import java.time.LocalDate
import java.time.LocalDateTime
import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.constants.SYSTEM
import team.gt.admin.application.enums.ReservationSource
import team.gt.admin.application.enums.ReservationStatus

class Reservation(
    val id: Long,
    val staffId: Long,
    val customer: Customer,
    val reservedVisitDate: LocalDate,
    val reservedVisitHour: Int,
    val reservedVisitQuarter: Int,
    val actualVisitDateTime: LocalDateTime?,
    val items: List<ReservationItem>,
    val status: ReservationStatus,
    val regTs: LocalDateTime,
    val updater: String,
    val updTs: LocalDateTime,
) {

    class Customer(
        val customerId: Long?,
        val reservationSource: ReservationSource,
    )

    companion object {

        fun createNew(
            staffId: Long,
            customerId: Long?,
            reservationSource: ReservationSource,
            reservedDate: LocalDate,
            reservedHour: Int,
            reservedQuarter: Int,
            items: List<ReservationItem>,
            regTs: LocalDateTime = LocalDateTime.now(),
        ): Reservation {

            return Reservation(
                id = JUST_CREATED_ID,
                staffId = staffId,
                customer = Customer(
                    customerId = customerId,
                    reservationSource = reservationSource,
                ),
                reservedVisitDate = reservedDate,
                reservedVisitHour = reservedHour,
                reservedVisitQuarter = reservedQuarter,
                actualVisitDateTime = null,
                items = items,
                status = ReservationStatus.NORMAL,
                regTs = regTs,
                updater = SYSTEM,
                updTs = regTs,
            )
        }
    }
}