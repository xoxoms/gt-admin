package team.gt.admin.application.domain.staff

import java.time.LocalDate
import java.time.LocalDateTime
import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.parser.staff.StaffScheduleStockParser

class StaffScheduleStock(
    val id: Long,
    val staffId: Long,
    val stock: LocalDateTime,
    val available: Boolean,
    val regTs: LocalDateTime,
    val updTs: LocalDateTime,
) {
    val quarter: Int
        get() = StaffScheduleStockParser.timeToQuarter(stock)

    fun isJustBefore(target: StaffScheduleStock): Boolean {
        if (this.stock.toLocalDate() != target.stock.toLocalDate()) { return false }

        val nextStockHour: Int
        val nextStockQuarter: Int
        if (this.quarter == 4) {
            nextStockHour = this.stock.hour + 1
            nextStockQuarter = 1
        } else {
            nextStockHour = this.stock.hour
            nextStockQuarter = this.quarter + 1
        }

        return target.stock.hour == nextStockHour && target.quarter == nextStockQuarter
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
                stock = StaffScheduleStockParser.quarterToTime(date, hour, quarter),
                available = available,
                regTs = regTs,
                updTs = regTs,
            )
        }
    }
}