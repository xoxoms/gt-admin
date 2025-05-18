package team.gt.admin.api.controller.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.text.DecimalFormat
import java.time.LocalDateTime
import team.gt.admin.application.enums.ReservationStatus
import team.gt.admin.application.parser.reservation.ReservationListView

class ReservationListViewResponse(
    val reservationId: Long,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    val reservedVisitDateTime: LocalDateTime,
    val staffId: Long,
    val staffNickname: String,
    val customerIdOrNull: Long?,
    val itemSummary: ReservationItemSummaryResponse,
    val reservationStatus: ReservationStatus,
    val reservationStatusKr: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    val actualVisitDateTimeOrNull: LocalDateTime?,
) {

    class ReservationItemSummaryResponse(
        val itemInfos: String,
        val totalPrice: Int,
        val totalPriceKr: String,
        val totalQuarterTaken: Int,
        val totalQuarterTakenKr: String,
    )

    companion object {
        private val decimalFormat = DecimalFormat("#,###")

        fun of(view: ReservationListView): ReservationListViewResponse {
            val itemInfos = mutableListOf<String>()
            var totalPrice = 0
            var totalQuarterTaken = 0

            view
                .reservationItems
                .forEach { item ->
                    itemInfos.add("${item.itemName} (예상소요시간: ${item.quarterTaken * 15}분, ${decimalFormat.format(item.price)}원)")
                    totalPrice += item.price
                    totalQuarterTaken += item.quarterTaken
                }
            
            return ReservationListViewResponse(
                reservationId = view.reservationId,
                reservedVisitDateTime = view.reservedVisitDateTime,
                staffId = view.staffId,
                staffNickname = view.staffNickname,
                customerIdOrNull = view.customerIdOrNull,
                itemSummary = ReservationItemSummaryResponse(
                    itemInfos = itemInfos.joinToString("\n"),
                    totalPrice = totalPrice,
                    totalPriceKr = "${decimalFormat.format(totalPrice)}원",
                    totalQuarterTaken = totalQuarterTaken,
                    totalQuarterTakenKr = "${totalQuarterTaken * 15}분",
                ),
                reservationStatus = view.reservationStatus,
                reservationStatusKr = view.reservationStatus.kr,
                actualVisitDateTimeOrNull = view.actualVisitDateTimeOrNull,
            )
        }
    }
}