package team.gt.admin.application.parser.reservation

import java.time.LocalDateTime
import team.gt.admin.application.domain.reservation.ReservationItem
import team.gt.admin.application.enums.ReservationSource
import team.gt.admin.application.enums.ReservationStatus

class ReservationListView(
    val reservationId: Long,
    val reservedVisitDateTime: LocalDateTime,
    val reservedHour: Int,
    val reservedQuarter: Int,
    val staffId: Long,
    val staffNickname: String,
    val customerIdOrNull: Long?,
    val customerNameOrNull: String?,
    val reservationSource: ReservationSource,
    val reservationStatus: ReservationStatus,
    val reservationItems: List<ReservationItem>,
    val actualVisitDateTimeOrNull: LocalDateTime?,
) {
    val displayItemName = reservationItems.joinToString(", ") { it.itemName }
    val totalQuarterTaken = reservationItems.sumOf { it.quarterTaken }
}