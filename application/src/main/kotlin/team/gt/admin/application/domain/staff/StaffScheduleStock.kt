package team.gt.admin.application.domain.staff

import java.time.LocalDate
import java.time.LocalDateTime
import team.gt.admin.application.constants.JUST_CREATED_ID

class StaffScheduleStock(
    val id: Long,
    val staffId: Long,
    val date: LocalDate,
    val hour: Int,
    val quarter: Int,
    val available: Boolean,
    val regTs: LocalDateTime,
    val updTs: LocalDateTime,
) {

    fun isJustBefore(target: StaffScheduleStock): Boolean {
        if (this.date != target.date) { return false }

        val nextStockHour: Int
        val nextStockQuarter: Int
        if (this.quarter == 4) {
            nextStockHour = this.hour + 1
            nextStockQuarter = 1
        } else {
            nextStockHour = this.hour
            nextStockQuarter = this.quarter + 1
        }

        // TODO. 24시 자정 처리
        return target.hour == nextStockHour && target.quarter == nextStockQuarter
    }

    companion object {

        fun createNew(
            staffId: Long,
            date: LocalDate,
            hour: Int,
            quarter: Int,
            available: Boolean,
            regTs: LocalDateTime = LocalDateTime.now(),
        ): StaffScheduleStock {

            return StaffScheduleStock(
                id = JUST_CREATED_ID,
                staffId = staffId,
                date = date,
                hour = hour,
                quarter = quarter,
                available = available,
                regTs = regTs,
                updTs = regTs,
            )
        }
    }
}