package team.gt.admin.application.domain.stock

import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import java.time.LocalDate
import org.junit.jupiter.api.assertThrows
import team.gt.admin.application.exception.CanNotBlockStockException
import team.gt.admin.application.storage.repository.StaffScheduleStockRepository

class StockBlockProcessorTest: FunSpec({

    test("시술 소요시간이 0쿼터면 블록하지 않는다.") {
        val totalQuarterTaken = 0

        val mockRepository = mockk<StaffScheduleStockRepository>()
        val sut = StockBlockProcessor(mockRepository)

        sut.blockOrThrow(1L, LocalDate.of(2025, 5, 1), 9, 1, totalQuarterTaken)

        verify(exactly = 0) { mockRepository.blockStock(any(), any(), any(), any()) }
    }

    test("update 쿼리 결과가 0이라면, 블록 실패로 예외를 던진다") {
        val totalQuarterTaken = 1

        val mockRepository = mockk<StaffScheduleStockRepository>()
        val sut = StockBlockProcessor(mockRepository)
        every { mockRepository.blockStock(any(), any(), any(), any()) } returns 0

        assertThrows<CanNotBlockStockException> { sut.blockOrThrow(1L, LocalDate.of(2025, 5, 1), 9, 1, totalQuarterTaken) }

        verify(exactly = 1) { mockRepository.blockStock(any(), any(), any(), any()) }
    }

    test("시술 소요시간이 1쿼터라면, DB 저장 1번 시도한다") {
        val totalQuarterTaken = 1

        val mockRepository = mockk<StaffScheduleStockRepository>()
        val sut = StockBlockProcessor(mockRepository)
        every { mockRepository.blockStock(any(), any(), any(), any()) } returns 1

        sut.blockOrThrow(1L, LocalDate.of(2025, 5, 1), 9, 1, totalQuarterTaken)

        verify(exactly = 1) { mockRepository.blockStock(any(), any(), any(), any()) }
    }

    test("19시 1쿼터부터 총 3쿼터 예약하면 [ 19시 1, 2, 3쿼터 ] 총 세번 저장시도해야한다") {
        val totalQuarterTaken = 3

        val mockRepository = mockk<StaffScheduleStockRepository>()
        val sut = StockBlockProcessor(mockRepository)
        val hourSlot = slot<Int>()
        val quarterSlot = slot<Int>()
        every { mockRepository.blockStock(any(), any(), capture(hourSlot), capture(quarterSlot)) } returns 1

        sut.blockOrThrow(1L, LocalDate.of(2025, 5, 1), 19, 1, totalQuarterTaken)

        verify(exactly = 1) { mockRepository.blockStock(any(), any(), 19, 1) }
        verify(exactly = 1) { mockRepository.blockStock(any(), any(), 19, 2) }
        verify(exactly = 1) { mockRepository.blockStock(any(), any(), 19, 3) }
    }

    test("19시 3쿼터부터 총 5쿼터 예약하면 [ 19시 3, 4쿼터, 20시 1, 2, 3 쿼터 ] 총 다섯번 저장시도해야한다") {
        val totalQuarterTaken = 5

        val mockRepository = mockk<StaffScheduleStockRepository>()
        val sut = StockBlockProcessor(mockRepository)
        val hourSlot = slot<Int>()
        val quarterSlot = slot<Int>()
        every { mockRepository.blockStock(any(), any(), capture(hourSlot), capture(quarterSlot)) } returns 1

        sut.blockOrThrow(1L, LocalDate.of(2025, 5, 1), 19, 3, totalQuarterTaken)

        verify(exactly = 1) { mockRepository.blockStock(any(), any(), 19, 3) }
        verify(exactly = 1) { mockRepository.blockStock(any(), any(), 19, 4) }
        verify(exactly = 1) { mockRepository.blockStock(any(), any(), 20, 1) }
        verify(exactly = 1) { mockRepository.blockStock(any(), any(), 20, 2) }
        verify(exactly = 1) { mockRepository.blockStock(any(), any(), 20, 3) }
    }
})