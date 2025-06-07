package team.gt.admin.api.controller.response

import java.time.LocalDate
import team.gt.admin.application.enums.ReservationSource
import team.gt.admin.application.enums.ReservationStatus

class ReservationDashboardResponse(
    val date: LocalDate,
    val workingTimes: List<Int>,
    val reservations: List<ReservationResponse>,
) {
    val perStaffs: List<PerStaff> = this.reservations
        .groupBy { it.staffId }
        .map { entry ->
            PerStaff(
                staffId = entry.key,
                staffNickname = entry.value.first().staffNickname,
                reservations = entry.value,
            )
        }

    class PerStaff(
        val staffId: Long,
        val staffNickname: String,
        val reservations: List<ReservationResponse>,
    ) {
        val hourQuarterMap: Map<String, List<ReservationResponse>> = reservations
            .groupBy { "${it.hour}_${it.quarter}" }
        val maxReservationCountPerQuarter: Int = hourQuarterMap
            .values
            .maxOfOrNull { bookedItems -> bookedItems.size } ?: 0
    }

    class ReservationResponse(
        val staffId: Long,
        val staffNickname: String,
        val reservationId: Long,
        val customerName: String,
        val displayItemNames: String,
        val hour: Int,
        val quarter: Int,
        val totalQuarterTaken: Int,
        val reservationSource: ReservationSource,
        val reservationStatus: ReservationStatus,
    ) {
    }
}