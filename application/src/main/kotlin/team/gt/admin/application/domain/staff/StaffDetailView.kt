package team.gt.admin.application.domain.staff

class StaffDetailView(
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