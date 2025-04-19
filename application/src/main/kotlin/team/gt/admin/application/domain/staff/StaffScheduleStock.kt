package team.gt.admin.application.domain.staff

import java.time.LocalDateTime

class StaffScheduleStock(
    val id: Long?,
    val staffId: Long,
    val dateHour: LocalDateTime,
    val quarter: Int,
    val available: Boolean,
    val regTs: LocalDateTime,
    val updTs: LocalDateTime,
) {

    fun isBefore(target: StaffScheduleStock): Boolean {
        val nextStockHour: LocalDateTime
        val nextStockQuarter: Int
        if (this.quarter == 4) {
            nextStockHour = this.dateHour.plusHours(1L)
            nextStockQuarter = 1
        } else {
            nextStockHour = this.dateHour
            nextStockQuarter = this.quarter + 1
        }

        return target.dateHour == nextStockHour && target.quarter == nextStockQuarter
    }

    companion object {

        fun createNew(
            staffId: Long,
            dateHour: LocalDateTime,
            quarter: Int,
            available: Boolean,
            regTs: LocalDateTime = LocalDateTime.now(),
        ): StaffScheduleStock {
            return StaffScheduleStock(
                id = 0L,
                staffId = staffId,
                dateHour = dateHour,
                quarter = quarter,
                available = available,
                regTs = regTs,
                updTs = regTs,
            )
        }
    }
}