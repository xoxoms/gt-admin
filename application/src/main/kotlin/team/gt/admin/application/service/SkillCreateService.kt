package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.skill.Skill
import team.gt.admin.application.domain.skill.SkillCreator

@Service
class SkillCreateService(
    private val skillCreator: SkillCreator,
) {

    fun create(name: String, creator: String) {
        skillCreator.create(Skill.createNew(name, creator))
    }
}