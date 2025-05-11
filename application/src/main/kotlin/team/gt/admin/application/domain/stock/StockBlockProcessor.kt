package team.gt.admin.application.domain.stock

import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.exception.DomainException
import team.gt.admin.application.storage.repository.StaffScheduleStockRepository

@Component
class StockBlockProcessor(
    private val staffScheduleStockRepository: StaffScheduleStockRepository,
) {
    @Transactional
    fun block(
        staffId: Long,
        date: LocalDate,
        hour: Int,
        quarter: Int,
        totalQuarterTaken: Int,
    ) {
        fun getReservationRangeTimes(): List<LocalDateTime> {
            val totalQuarterTaken = items.sumOf { it.quarterTaken }
            val times = mutableListOf<LocalDateTime>()
            for (i in 0 ..< totalQuarterTaken) {
                times.add(reservedVisitDateTime.plusMinutes(i * 15L))
            }

            return times
        }

        times.forEach { time ->
            val result = staffScheduleStockRepository.blockStock(staffId, time)
            if (result != 1) {
                throw DomainException()
            }
        }
    }
}