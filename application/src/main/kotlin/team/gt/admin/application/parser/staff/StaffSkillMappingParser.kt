package team.gt.admin.application.parser.staff

import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.domain.staff.StaffSkillMapping
import team.gt.admin.application.storage.entity.StaffSkillMappingEntity

object StaffSkillMappingParser {

    fun fromDomain(domain: StaffSkillMapping): StaffSkillMappingEntity {

        return StaffSkillMappingEntity(
            id = if (domain.id == JUST_CREATED_ID) null else domain.id,
            staffId = domain.staffId,
            skillId = domain.skillId,
            price = domain.price,
            quarterTaken = domain.quarterTaken,
            deleted = domain.deleted,
            regTs = domain.regTs,
            updTs = domain.updTs,
        )
    }
}