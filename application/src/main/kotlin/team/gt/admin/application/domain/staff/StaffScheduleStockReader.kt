package team.gt.admin.application.domain.staff

import java.time.LocalDate
import org.springframework.stereotype.Component
import team.gt.admin.application.parser.staff.StaffScheduleStockParser
import team.gt.admin.application.storage.repository.StaffScheduleStockRepository

@Component
class StaffScheduleStockReader(
    private val staffScheduleStockRepository: StaffScheduleStockRepository,
) {

    fun readByStaffIdAndDate(staffId: Long, date: LocalDate): List<StaffScheduleStock> {
        return staffScheduleStockRepository.findByStaffIdAndStockDate(staffId, date)
            .map(StaffScheduleStockParser::toDomain)
    }
}