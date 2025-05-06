package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.SkillEntity

interface SkillRepository: JpaRepository<SkillEntity, Long> {

    fun findByDeletedFalse(): List<SkillEntity>
}