package team.gt.admin.application.storage.repository

import java.time.LocalDateTime
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.storage.entity.StaffScheduleStockEntity

interface StaffScheduleStockRepository: JpaRepository<StaffScheduleStockEntity, Long> {
    fun findByStaffIdAndDateHourGreaterThanEqual(staffId: Long, dateHour: LocalDateTime): List<StaffScheduleStockEntity>

    @Query(
        nativeQuery = true,
        value = """
        update staff_schedule_stock 
        set available = false 
        where staffId = :staffId and dateHour = :dateHour and available = false 
    """
    )
    @Modifying
    @Transactional(propagation = Propagation.MANDATORY)
    fun blockStock(staffId: Long, dateHour: LocalDateTime): Int
}