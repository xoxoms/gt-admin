package team.gt.admin.application.domain.skill

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.skill.SkillParser
import team.gt.admin.application.storage.repository.SkillRepository

@Component
class SkillCreator(
    private val skillRepository: SkillRepository,
) {

    fun create(skill: Skill): Skill {
        val saved = skillRepository.save(SkillParser.fromDomain(skill))
        return SkillParser.toDomain(saved)
    }
}