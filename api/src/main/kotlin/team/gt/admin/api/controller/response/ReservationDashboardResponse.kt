package team.gt.admin.api.controller.response

class ReservationDashboardResponse(

) {

    class PerStaff(
        val staffId: Long,
        val staffNickname: String,

    )

    class QuarterItem(
        val hour: Int,
        val quarter: Int,
        val booked: Boolean,
    )

    class BookedItem(
    )
}