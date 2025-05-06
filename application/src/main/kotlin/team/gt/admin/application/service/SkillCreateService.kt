package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.skill.Skill
import team.gt.admin.application.domain.skill.SkillCreator

@Service
class SkillCreateService(
    private val skillCreator: SkillCreator,
) {

    fun create(
        name: String,
        defaultPrice: Int,
        defaultQuarterTaken: Int,
        creator: String
    ): Skill {
        return skillCreator.create(
            Skill.createNew(
                name = name,
                defaultPrice = defaultPrice,
                defaultQuarterTaken = defaultQuarterTaken,
                creator = creator,
            )
        )
    }
}