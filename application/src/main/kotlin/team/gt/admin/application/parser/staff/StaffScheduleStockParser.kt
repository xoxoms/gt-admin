package team.gt.admin.application.parser.staff

import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.domain.staff.StaffScheduleStock
import team.gt.admin.application.storage.entity.StaffScheduleStockEntity

object StaffScheduleStockParser {

    fun fromDomain(domain: StaffScheduleStock): StaffScheduleStockEntity {
        return StaffScheduleStockEntity(
            id = if (domain.id == JUST_CREATED_ID) null else domain.id,
            staffId = domain.staffId,
            dateHour = domain.dateHour,
            quarter = domain.quarter,
            available = domain.available,
            regTs = domain.regTs,
            updTs = domain.updTs,
        )
    }

    fun toDomain(entity: StaffScheduleStockEntity): StaffScheduleStock {
        return StaffScheduleStock(
            id = entity.id!!,
            staffId = entity.staffId,
            dateHour = entity.dateHour,
            quarter = entity.quarter,
            available = entity.available,
            regTs = entity.regTs,
            updTs = entity.updTs,
        )
    }
}