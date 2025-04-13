package team.gt.admin.application.domain.crew

class Crew(
    val id: Long,
    val positionId: Long,
    val personId: Long,
    val name: String,
    val phone: String,
) {
}