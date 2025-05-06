package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.skill.Skill
import team.gt.admin.application.domain.skill.SkillReader

@Service
class SkillReadService(
    private val skillReader: SkillReader,
) {

    fun readAll(): List<Skill> {
        return skillReader.readAll()
    }
}