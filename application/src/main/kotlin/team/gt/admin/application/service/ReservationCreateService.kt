package team.gt.admin.application.service

import java.time.LocalDate
import org.springframework.stereotype.Service
import team.gt.admin.application.domain.reservation.ReservationCreator
import team.gt.admin.application.domain.reservation.ReservationItemTarget
import team.gt.admin.application.domain.skill.SkillReader
import team.gt.admin.application.domain.staff.AvailableScheduleCalculator
import team.gt.admin.application.domain.staff.StaffScheduleStockReader
import team.gt.admin.application.enums.ReservationSource

@Service
class ReservationCreateService(
    private val staffScheduleStockReader: StaffScheduleStockReader,
    private val skillReader: SkillReader,
    private val reservationCreator: ReservationCreator,
) {

    fun create(
        staffId: Long,
        customerId: Long?,
        source: ReservationSource,
        date: LocalDate,
        hour: Int,
        quarter: Int,
        itemTargets: List<ReservationItemTarget>
    ) {

        val staffScheduleStocks = staffScheduleStockReader.readByStaffIdAndDateInclusive(staffId, date.atStartOfDay())
//        AvailableScheduleCalculator.calculate(quarterTaken, staffScheduleStocks)
    }
}