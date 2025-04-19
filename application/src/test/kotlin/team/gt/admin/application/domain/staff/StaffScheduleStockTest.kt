package team.gt.admin.application.domain.staff

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDateTime

class StaffScheduleStockTest: BehaviorSpec({
    given("9시 첫번째 쿼터가") {
        val staffId = 1L
        val before = StaffScheduleStock.createNew(
            staffId = staffId,
            dateHour = LocalDateTime.of(2024, 1, 1, 9, 0, 0),
            quarter = 1,
            available = true
        )

        `when`("9시 첫번째 쿼터의 isBefore()인지 확인 시") {
            val dateHour = LocalDateTime.of(2024, 1, 1, 9, 0, 0)
            val quarter = 1

            then("false를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    dateHour = dateHour,
                    quarter = quarter,
                    available = true
                )

                before.isBefore(after).shouldBeFalse()
            }
        }

        `when`("9시 두번째 쿼터의 isBefore()인지 확인 시") {
            val dateHour = LocalDateTime.of(2024, 1, 1, 9, 0, 0)
            val quarter = 2

            then("true를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    dateHour = dateHour,
                    quarter = quarter,
                    available = true
                )

                before.isBefore(after).shouldBeTrue()
            }
        }

        `when`("9시 세번째 쿼터의 isBefore()인지 확인 시") {
            val dateHour = LocalDateTime.of(2024, 1, 1, 9, 0, 0)
            val quarter = 3

            then("true를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    dateHour = dateHour,
                    quarter = quarter,
                    available = true
                )

                before.isBefore(after).shouldBeFalse()
            }
        }
    }

    given("9시 네번째 쿼터가") {
        val staffId = 1L
        val before = StaffScheduleStock.createNew(
            staffId = staffId,
            dateHour = LocalDateTime.of(2024, 1, 1, 9, 0, 0),
            quarter = 4,
            available = true
        )

        `when`("10시 첫번째 쿼터의 isBefore()인지 확인 시") {
            val dateHour = LocalDateTime.of(2024, 1, 1, 10, 0, 0)
            val quarter = 1

            then("true를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    dateHour = dateHour,
                    quarter = quarter,
                    available = true
                )

                before.isBefore(after).shouldBeTrue()
            }
        }
    }

    given("9시 네번째 쿼터가") {
        val staffId = 1L
        val before = StaffScheduleStock.createNew(
            staffId = staffId,
            dateHour = LocalDateTime.of(2024, 1, 1, 9, 0, 0),
            quarter = 4,
            available = true
        )

        `when`("다음날의 10시 첫번째 쿼터의 isBefore()인지 확인 시") {
            val dateHour = LocalDateTime.of(2024, 1, 2, 10, 0, 0)
            val quarter = 1

            then("false를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    dateHour = dateHour,
                    quarter = quarter,
                    available = false
                )

                before.isBefore(after).shouldBeFalse()
            }
        }
    }
})