package team.gt.admin.application.domain.crew

import team.gt.admin.application.storage.entity.CrewEntity

class Crew(
    val id: Long,
    val positionId: Long,
    val personId: Long,
    val nickname: String,
    val mainPhone: String,
) {

    companion object {

        fun fromEntity(entity: CrewEntity): Crew {
            return Crew(
                id = entity.id!!,
                positionId = entity.positionId,
                personId = entity.personId,
                nickname = entity.nickname,
                mainPhone = entity.mainPhone,
            )
        }
    }
}