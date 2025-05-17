package team.gt.admin.application.domain.staff

import org.springframework.stereotype.Component
import team.gt.admin.application.storage.repository.StaffSkillMappingRepository

@Component
class StaffSkillReader(
    private val staffSkillMappingRepository: StaffSkillMappingRepository,
) {

    fun read(staffId: Long, skillIds: List<Long>): List<StaffSkillDetail> {
        return staffSkillMappingRepository.getStaffSkills(staffId, skillIds)
    }
}