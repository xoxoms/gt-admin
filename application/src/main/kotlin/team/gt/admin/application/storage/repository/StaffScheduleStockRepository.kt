package team.gt.admin.application.storage.repository

import java.time.LocalDate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.storage.entity.StaffScheduleStockEntity

interface StaffScheduleStockRepository: JpaRepository<StaffScheduleStockEntity, Long> {
    fun findByStaffIdAndStockDate(staffId: Long, date: LocalDate): List<StaffScheduleStockEntity>

    @Query(
        nativeQuery = true,
        value = """
        update staff_schedule_stock 
        set available = false 
        where staffId = :staffId and stockDate = :stockDate and stockHour = :hour and quarter = :quarter and available = true 
    """
    )
    @Modifying
    @Transactional(propagation = Propagation.MANDATORY)
    fun blockStock(staffId: Long, stockDate: LocalDate, hour: Int, quarter: Int): Int
}