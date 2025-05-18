package team.gt.admin.application.domain.stock

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.exception.CanNotBlockStockException
import team.gt.admin.application.storage.repository.StaffScheduleStockRepository

@Component
class StockBlockProcessor(
    private val staffScheduleStockRepository: StaffScheduleStockRepository,
) {

    @Transactional
    fun blockOrThrow(
        staffId: Long,
        date: LocalDate,
        hour: Int,
        quarter: Int,
        totalQuarterTaken: Int,
    ) {
        var stockHour = hour
        var stockQuarter = quarter
        for (i in 0 ..< totalQuarterTaken) {
            if (stockQuarter == 5) {
                stockQuarter = 1
                stockHour++
            }

            val result = staffScheduleStockRepository.blockStock(staffId, date, stockHour, stockQuarter)
            if (result != 1) {
                throw CanNotBlockStockException()
            }

            stockQuarter++
        }
    }
}