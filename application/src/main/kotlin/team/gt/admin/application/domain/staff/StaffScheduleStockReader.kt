package team.gt.admin.application.domain.staff

import java.time.LocalDateTime
import org.springframework.stereotype.Component
import team.gt.admin.application.parser.staff.StaffScheduleStockParser
import team.gt.admin.application.storage.repository.StaffScheduleStockRepository

@Component
class StaffScheduleStockReader(
    private val staffScheduleStockRepository: StaffScheduleStockRepository,
) {

    fun readByStaffIdAndDateInclusive(staffId: Long, midnight: LocalDateTime): List<StaffScheduleStock> {
        return staffScheduleStockRepository.findByStaffIdAndDateHourGreaterThanEqual(staffId, midnight)
            .map(StaffScheduleStockParser::toDomain)
    }
}