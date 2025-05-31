package team.gt.admin.api.controller.response

import java.time.LocalDate
import team.gt.admin.application.enums.VisitSource

class ReservationDashboardResponse(
    val date: LocalDate,
    val workingTimes: List<Int>,
    val perStaffs: List<PerStaff>,
) {

    class PerStaff(
        val staffId: Long,
        val staffNickname: String,
        val bookings: List<BookedItem>,
    ) {

    }

    class BookedItem(
        val customerName: String,
        val displayItemNames: String,
        val hour: Int,
        val quarter: Int,
        val totalQuarterTaken: Int,
        val booked: Boolean,
        val source: VisitSource,
    )
}