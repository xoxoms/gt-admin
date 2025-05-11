package team.gt.admin.application.domain.staff

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class AvailableScheduleCalculatorTest: BehaviorSpec({
    val staffId = 1L
    given("9시에 1,2,3 쿼터가 존재하고") {
        val stocks = listOf(
            StaffScheduleStock.createNew(
                staffId = staffId,
                date = LocalDate.of(2024, 1, 1),
                hour = 9,
                quarter = 1,
                available = true
            ),
            StaffScheduleStock.createNew(
                staffId = staffId,
                date = LocalDate.of(2024, 1, 1),
                hour = 9,
                quarter = 2,
                available = true
            ),
            StaffScheduleStock.createNew(
                staffId = staffId,
                date = LocalDate.of(2024, 1, 1),
                hour = 9,
                quarter = 3,
                available = true
            )
        )

        `when`("시술에 3쿼터가 걸린다면") {
            val quarterTaken = 3
            then("9시 1 쿼터만 반환한다.") {
                val calculated = AvailableScheduleCalculator.calculate(quarterTaken, stocks)
                calculated.shouldHaveSize(1)
                calculated.first() shouldBe stocks[0]
            }
        }

        `when`("시술에 2쿼터가 걸린다면") {
            val quarterTaken = 2
            then("9시 1, 2 쿼터만 반환한다.") {
                val calculated = AvailableScheduleCalculator.calculate(quarterTaken, stocks)
                calculated.shouldHaveSize(2)
                calculated[0] shouldBe stocks[0]
                calculated[1] shouldBe stocks[1]
            }
        }

        `when`("시술에 1쿼터가 걸린다면") {
            val quarterTaken = 1
            then("9시 1, 2, 3 쿼터 모두 반환한다.") {
                val calculated = AvailableScheduleCalculator.calculate(quarterTaken, stocks)
                calculated.shouldHaveSize(3)
                calculated[0] shouldBe stocks[0]
                calculated[1] shouldBe stocks[1]
                calculated[2] shouldBe stocks[2]
            }
        }
    }

    given("9시 1,2,3 쿼터 모두 비활성화 상태이고") {
        val stocks = listOf(
            StaffScheduleStock.createNew(
                staffId = staffId,
                date = LocalDate.of(2024, 1, 1),
                hour = 9,
                quarter = 1,
                available = false
            ),
            StaffScheduleStock.createNew(
                staffId = staffId,
                date = LocalDate.of(2024, 1, 1),
                hour = 9,
                quarter = 2,
                available = false
            ),
            StaffScheduleStock.createNew(
                staffId = staffId,
                date = LocalDate.of(2024, 1, 1),
                hour = 9,
                quarter = 3,
                available = false
            )
        )

        `when`("시술에 3쿼터가 걸린다면") {
            val quarterTaken = 3
            then("빈 배열을 반환한다.") {
                val calculated = AvailableScheduleCalculator.calculate(quarterTaken, stocks)
                calculated.shouldBeEmpty()
            }
        }

        `when`("시술에 2쿼터가 걸린다면") {
            val quarterTaken = 2
            then("빈 배열을 반환한다.") {
                val calculated = AvailableScheduleCalculator.calculate(quarterTaken, stocks)
                calculated.shouldBeEmpty()
            }
        }

        `when`("시술에 1쿼터가 걸린다면") {
            val quarterTaken = 1
            then("빈 배열을 반환한다.") {
                val calculated = AvailableScheduleCalculator.calculate(quarterTaken, stocks)
                calculated.shouldBeEmpty()
            }
        }
    }
})