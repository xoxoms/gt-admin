package team.gt.admin.application.parser.skill

import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.domain.skill.Skill
import team.gt.admin.application.storage.entity.SkillEntity

object SkillParser {

    fun fromDomain(domain: Skill): SkillEntity {

        return SkillEntity(
            id = if (domain.id == JUST_CREATED_ID) null else domain.id,
            name = domain.name,
            defaultPrice = domain.defaultPrice,
            defaultQuarterTaken = domain.defaultQuarterTaken,
            deleted = domain.deleted,
            creator = domain.creator,
            regTs = domain.regTs,
            updater = domain.updater,
            updTs = domain.updTs,
        )
    }

    fun toDomain(entity: SkillEntity): Skill {

        return Skill(
            id = entity.id!!,
            name = entity.name,
            defaultPrice = entity.defaultPrice,
            defaultQuarterTaken = entity.defaultQuarterTaken,
            deleted = entity.deleted,
            creator = entity.creator,
            regTs = entity.regTs,
            updater = entity.updater,
            updTs = entity.updTs,
        )
    }
}