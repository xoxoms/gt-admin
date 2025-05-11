package team.gt.admin.application.parser.staff

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.domain.staff.StaffScheduleStock
import team.gt.admin.application.exception.DomainException
import team.gt.admin.application.storage.entity.StaffScheduleStockEntity

object StaffScheduleStockParser {

    fun fromDomain(domain: StaffScheduleStock): StaffScheduleStockEntity {
        return StaffScheduleStockEntity(
            id = if (domain.id == JUST_CREATED_ID) null else domain.id,
            staffId = domain.staffId,
            stock = domain.stock,
            available = domain.available,
            regTs = domain.regTs,
            updTs = domain.updTs,
        )
    }

    fun toDomain(entity: StaffScheduleStockEntity): StaffScheduleStock {
        return StaffScheduleStock(
            id = entity.id!!,
            staffId = entity.staffId,
            stock = entity.stock,
            available = entity.available,
            regTs = entity.regTs,
            updTs = entity.updTs,
        )
    }

    fun timeToQuarter(time: LocalDateTime): Int {
        return when (time.minute) {
            0 -> 1
            15 -> 2
            30 -> 3
            45 -> 4
            else -> {
                throw DomainException()
            }
        }
    }

    fun quarterToTime(date: LocalDate, hour: Int, quarter: Int): LocalDateTime {
        val minute = (quarter - 1) * 15
        return LocalDateTime.of(date, LocalTime.of(hour, minute))
    }
}