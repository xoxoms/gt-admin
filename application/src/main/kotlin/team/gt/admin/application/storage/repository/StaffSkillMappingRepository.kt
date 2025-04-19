package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.StaffSkillMappingEntity

interface StaffSkillMappingRepository: JpaRepository<StaffSkillMappingEntity, Long> {
    fun findByStaffId(staffId: Long): List<StaffSkillMappingEntity>
}