package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import team.gt.admin.application.domain.staff.StaffSkillDetail
import team.gt.admin.application.storage.entity.StaffSkillMappingEntity

interface StaffSkillMappingRepository: JpaRepository<StaffSkillMappingEntity, Long> {

    @Query(
        """
            select new team.gt.admin.application.domain.staff.StaffSkillDetail(
                m.staffId,
                m.skillId,
                s.name,
                m.price,
                m.quarterTaken
            )
            from StaffSkillMappingEntity m
            join SkillEntity s on s.id = m.skillId
            where m.staffId = :staffId and m.skillId in (:skillIds)
        """
    )
    fun getStaffSkills(staffId: Long, skillIds: List<Long>): List<StaffSkillDetail>
}