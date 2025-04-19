package team.gt.admin.application.service

import java.time.LocalDate
import org.springframework.stereotype.Service
import team.gt.admin.application.domain.staff.AvailableScheduleCalculator
import team.gt.admin.application.domain.staff.StaffScheduleStock
import team.gt.admin.application.domain.staff.StaffScheduleStockReader

@Service
class BookingAvailabilityService(
    private val staffScheduleStockReader: StaffScheduleStockReader,
) {

    fun read(
        staffId: Long,
        date: LocalDate,
        quarterTaken: Int,
    ): List<StaffScheduleStock> {
        val staffScheduleStocks = staffScheduleStockReader.readByStaffIdAndDateInclusive(staffId, date.atStartOfDay())
        return AvailableScheduleCalculator.calculate(quarterTaken, staffScheduleStocks)
    }
}