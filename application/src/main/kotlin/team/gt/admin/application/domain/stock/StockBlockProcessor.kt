package team.gt.admin.application.domain.stock

import java.time.LocalDate
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
        for (i in 0 ..< totalQuarterTaken) {
            val stockQuarter = ((quarter + i) % 4) + 1
            val stockHour = ((quarter + i) / 4) + hour
            // TODO. 시간 쿼터 계산 제대로 다시

            val result = staffScheduleStockRepository.blockStock(staffId, date, stockHour, stockQuarter)
            if (result != 1) {
                throw CanNotBlockStockException()
            }
        }
    }
}