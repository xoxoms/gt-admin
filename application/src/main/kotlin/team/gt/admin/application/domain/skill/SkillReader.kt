package team.gt.admin.application.domain.skill

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.skill.SkillParser
import team.gt.admin.application.storage.repository.SkillRepository

@Component
class SkillReader(
    private val skillRepository: SkillRepository,
) {

    fun readAll(): List<Skill> {
        return skillRepository.findByDeletedFalse()
            .map(SkillParser::toDomain)
    }
}