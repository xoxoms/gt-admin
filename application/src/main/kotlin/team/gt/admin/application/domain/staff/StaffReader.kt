package team.gt.admin.application.domain.staff

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.staff.StaffParser
import team.gt.admin.application.storage.repository.StaffRepository

@Component
class StaffReader(
    private val staffRepository: StaffRepository,
) {

    fun readAllWorkingStaffs(): List<Staff> {
        return staffRepository.findAllByResignedIsFalse()
            .map(StaffParser::toDomain)
    }
}