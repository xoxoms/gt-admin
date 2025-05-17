package team.gt.admin.application.domain.staff

class StaffSkillDetail(
    val staffId: Long,
    val skillId: Long,
    val skillName: String,
    val price: Int,
    val quarterTaken: Int,
) {
}