package team.gt.admin.application.domain.staff

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.staff.StaffScheduleStockParser
import team.gt.admin.application.storage.repository.StaffScheduleStockRepository

@Component
class StaffScheduleStockCreator(
    private val staffScheduleStockRepository: StaffScheduleStockRepository,
) {

    fun create(staffScheduleStocks: List<StaffScheduleStock>) {
        staffScheduleStockRepository.saveAll(staffScheduleStocks.map(StaffScheduleStockParser::fromDomain))
    }
}