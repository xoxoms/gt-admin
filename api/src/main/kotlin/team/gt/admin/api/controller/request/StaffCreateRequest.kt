package team.gt.admin.api.controller.request

class StaffCreateRequest(
    val name: String,
    val privatePhone: String,
    val privateAddress: String,
    val nickname: String,
    val mainPhone: String,
    val positionId: Long,
    val skillMappingRequests: List<SkillMappingRequest>,
) {

    class SkillMappingRequest(
        val skillId: Long,
        val price: Int,
        val quarterTaken: Int,
    )
}
