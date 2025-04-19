package team.gt.admin.application.parser.position

import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.domain.position.Position
import team.gt.admin.application.storage.entity.PositionEntity

object PositionParser {

    fun fromDomain(domain: Position): PositionEntity {

        return PositionEntity(
            id = if (domain.id == JUST_CREATED_ID) null else domain.id,
            name = domain.name,
            deleted = domain.deleted,
            creator = domain.creator,
            regTs = domain.regTs,
            updater = domain.updater,
            updTs = domain.updTs,
        )
    }

    fun toDomain(entity: PositionEntity): Position {

        return Position(
            id = entity.id!!,
            name = entity.name,
            deleted = entity.deleted,
            creator = entity.creator,
            regTs = entity.regTs,
            updater = entity.updater,
            updTs = entity.updTs,
        )
    }
}