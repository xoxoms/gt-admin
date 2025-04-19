package team.gt.admin.application.storage.repository

import java.time.LocalDateTime
import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.StaffScheduleStockEntity

interface StaffScheduleStockRepository: JpaRepository<StaffScheduleStockEntity, Long> {

    fun findByStaffIdAndDateHourGreaterThanEqual(staffId: Long, midnight: LocalDateTime): List<StaffScheduleStockEntity>
}