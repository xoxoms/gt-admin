package team.gt.admin.application.service

import java.time.LocalDate
import org.springframework.stereotype.Service
import team.gt.admin.application.domain.staff.Staff
import team.gt.admin.application.domain.staff.StaffReader
import team.gt.admin.application.domain.staff.StaffScheduleStock
import team.gt.admin.application.domain.staff.StaffScheduleStockCreator

@Service
class StaffScheduleStockCreateService(
    private val staffReader: StaffReader,
    private val staffScheduleStockCreator: StaffScheduleStockCreator,
) {

    fun create(date: LocalDate, startHour: Int, endHour: Int) {
        val staffs = staffReader.readAllWorkingStaffs()
        staffs.forEach { staff ->
            staffScheduleStockCreator.create(
                create(
                    startHour = startHour,
                    endHour = endHour,
                    staff = staff,
                    date = date
                )
            )
        }
    }

    private fun create(
        startHour: Int,
        endHour: Int,
        staff: Staff,
        date: LocalDate,
    ): List<StaffScheduleStock> {
        return (startHour..endHour).flatMap { hour ->
            (1..4).map { quarter ->
                StaffScheduleStock.createNew(
                    staffId = staff.id,
                    dateHour = date.atTime(hour, 0),
                    quarter = quarter,
                    available = true
                )
            }
        }
    }
}