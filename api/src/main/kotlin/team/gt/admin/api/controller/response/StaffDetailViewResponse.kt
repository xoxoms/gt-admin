package team.gt.admin.api.controller.response

class StaffDetailViewResponse(
    val staffId: Long,
    val positionId: Long,
    val personId: Long,

    val privatePhone: String,
    val privateAddress: String,

    val nickname: String,
    val mainPhone: String,

    val position: String,
) {
}