package team.gt.admin.api.controller.request

class CrewCreateRequest(
    val name: String,
    val privatePhone: String,
    val privateAddress: String,
    val nickname: String,
    val mainPhone: String,
    val positionId: Long,
) {

}