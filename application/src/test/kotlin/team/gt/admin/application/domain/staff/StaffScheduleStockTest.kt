package team.gt.admin.application.domain.staff

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate

class StaffScheduleStockTest: BehaviorSpec({
    val date = LocalDate.of(2024, 1, 1)
    given("9시 첫번째 쿼터가") {
        val staffId = 1L
        val before = StaffScheduleStock.createNew(
            staffId = staffId,
            date = date,
            hour = 9,
            quarter = 1,
            available = true
        )

        `when`("9시 첫번째 쿼터의 isJustBefore()인지 확인 시") {
            val hour = 9
            val quarter = 1

            then("false를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    date = date,
                    hour = hour,
                    quarter = quarter,
                    available = true
                )

                before.isJustBefore(after).shouldBeFalse()
            }
        }

        `when`("9시 두번째 쿼터의 isJustBefore()인지 확인 시") {
            val hour = 9
            val quarter = 2

            then("true를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    date = date,
                    hour = hour,
                    quarter = quarter,
                    available = true
                )

                before.isJustBefore(after).shouldBeTrue()
            }
        }

        `when`("9시 세번째 쿼터의 isJustBefore()인지 확인 시") {
            val hour = 9
            val quarter = 3

            then("true를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    date = date,
                    hour = hour,
                    quarter = quarter,
                    available = true
                )

                before.isJustBefore(after).shouldBeFalse()
            }
        }
    }

    given("9시 네번째 쿼터가") {
        val staffId = 1L
        val before = StaffScheduleStock.createNew(
            staffId = staffId,
            date = date,
            hour = 9,
            quarter = 4,
            available = true
        )

        `when`("10시 첫번째 쿼터의 isJustBefore()인지 확인 시") {
            val hour = 10
            val quarter = 1

            then("true를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    date = date,
                    hour = hour,
                    quarter = quarter,
                    available = true
                )

                before.isJustBefore(after).shouldBeTrue()
            }
        }
    }

    given("9시 네번째 쿼터가") {
        val staffId = 1L
        val before = StaffScheduleStock.createNew(
            staffId = staffId,
            date = date,
            hour = 9,
            quarter = 4,
            available = true
        )

        `when`("다음날의 10시 첫번째 쿼터의 isJustBefore()인지 확인 시") {
            val nextDate = LocalDate.of(2024, 1, 2)
            val hour = 10
            val quarter = 1

            then("false를 응답해야한다.") {
                val after = StaffScheduleStock.createNew(
                    staffId = staffId,
                    date = nextDate,
                    hour = hour,
                    quarter = quarter,
                    available = false
                )

                before.isJustBefore(after).shouldBeFalse()
            }
        }
    }
})