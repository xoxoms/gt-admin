package team.gt.admin.application.parser.skill

import team.gt.admin.application.domain.skill.Skill
import team.gt.admin.application.storage.entity.SkillEntity

object SkillParser {

    fun fromDomain(domain: Skill): SkillEntity {

        return SkillEntity(
            id = domain.id,
            name = domain.name,
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
            deleted = entity.deleted,
            creator = entity.creator,
            regTs = entity.regTs,
            updater = entity.updater,
            updTs = entity.updTs,
        )
    }
}