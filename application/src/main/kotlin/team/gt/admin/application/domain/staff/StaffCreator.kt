package team.gt.admin.application.domain.staff

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.staff.StaffParser
import team.gt.admin.application.storage.repository.StaffRepository

@Component
class StaffCreator(
    private val staffRepository: StaffRepository,
) {

    fun create(staff: Staff): Staff {
        val saved = staffRepository.save(StaffParser.fromDomain(staff))
        return StaffParser.toDomain(saved)
    }
}