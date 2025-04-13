package team.gt.admin.application.domain.position

import team.gt.admin.application.storage.entity.PositionEntity

class Position(
    val id: Long,
    val name: String,
) {

    companion object {

        fun fromEntity(
            entity: PositionEntity,
        ): Position {
            return Position(
                id = entity.id!!,
                name = entity.name,
            )
        }
    }
}