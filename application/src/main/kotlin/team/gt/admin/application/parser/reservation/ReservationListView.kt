package team.gt.admin.application.parser.reservation

import java.time.LocalDateTime
import team.gt.admin.application.domain.reservation.ReservationItem
import team.gt.admin.application.enums.ReservationStatus

class ReservationListView(
    val reservationId: Long,
    val reservedVisitDateTime: LocalDateTime,
    val staffId: Long,
    val staffNickname: String,
    val customerIdOrNull: Long?,
    val reservationItems: List<ReservationItem>,
    val reservationStatus: ReservationStatus,
    val actualVisitDateTimeOrNull: LocalDateTime?,
) {
}