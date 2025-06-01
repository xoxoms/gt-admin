package team.gt.admin.api.controller.response

import java.time.LocalDate
import team.gt.admin.application.enums.VisitSource

class ReservationDashboardResponse(
    val date: LocalDate,
    val workingTimes: List<Int>,
    val perStaffs: List<PerStaff>,
) {

    class PerStaff(
        val staffId: Long,
        val staffNickname: String,
        val bookings: List<BookedItem>,
    ) {
        val hourMap: Map<Int, List<QuarterItem>>
        val hourQuarterMap: Map<String, QuarterItem>
        val hourQuarterIdMap: Map<String, QuarterItem>
        val maxBookedPerQuarter: Int

        init {
            val quarterItems = bookings.flatMap { it.makeQuarterItems() }

            this.hourMap = quarterItems.groupBy { it.hour }
            this.hourQuarterMap = quarterItems
                .associateBy { "${it.hour}_${it.quarter}" }
            this.hourQuarterIdMap = quarterItems
                .associateBy { "${it.hour}_${it.quarter}_${it.bookingItemId}" }
            this.maxBookedPerQuarter = hourMap
                .values
                .maxOfOrNull { bookedItems -> bookedItems.size } ?: 0
        }
    }

    class QuarterItem(
        val bookingItemId: Long,
        val startQuarter: Boolean,
        val endQuarter: Boolean,
        val hour: Int,
        val quarter: Int,
        val customerName: String,
        val source: VisitSource,
        val displayItemNames: String,
        val totalQuarterTaken: Int,
    )

    class BookedItem(
        val bookingItemId: Long,
        val customerName: String,
        val displayItemNames: String,
        val hour: Int,
        val quarter: Int,
        val totalQuarterTaken: Int,
        val booked: Boolean,
        val source: VisitSource,
    ) {
        fun makeQuarterItems(): List<QuarterItem> {
            val result = mutableListOf<QuarterItem>()
            var currentHour = hour
            var currentQuarter = quarter

            for (i in quarter ..< quarter + totalQuarterTaken) {
                if (currentQuarter == 5) {
                    currentHour++
                    currentQuarter = 1
                }

                val start = i == quarter
                val end = i == quarter + totalQuarterTaken - 1

                result.add(
                    QuarterItem(
                        bookingItemId = bookingItemId,
                        startQuarter = start,
                        endQuarter = end,
                        hour = currentHour,
                        quarter = currentQuarter,
                        customerName = customerName,
                        source = source,
                        displayItemNames = displayItemNames,
                        totalQuarterTaken = totalQuarterTaken,
                    )
                )

                currentQuarter++
            }

            return result
        }
    }
}