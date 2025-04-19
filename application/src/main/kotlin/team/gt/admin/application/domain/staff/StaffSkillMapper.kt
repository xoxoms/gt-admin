package team.gt.admin.application.domain.staff

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.staff.StaffSkillMappingParser
import team.gt.admin.application.storage.repository.StaffSkillMappingRepository

@Component
class StaffSkillMapper(
    private val staffSkillMappingRepository: StaffSkillMappingRepository,
) {

    fun create(staffSkillMappings: List<StaffSkillMapping>) {
        staffSkillMappingRepository.saveAll(
            staffSkillMappings
                .map(StaffSkillMappingParser::fromDomain)
        )
    }
}