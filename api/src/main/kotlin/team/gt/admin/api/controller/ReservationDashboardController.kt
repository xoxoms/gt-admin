package team.gt.admin.api.controller

import java.time.LocalDate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.ReservationDashboardResponse
import team.gt.admin.api.controller.response.ReservationDashboardResponse.ReservationResponse
import team.gt.admin.application.service.ReservationReadService

@RestController
class ReservationDashboardController(
    private val reservationReadService: ReservationReadService,
) {

    @GetMapping("/api/v1/reservation-dashboard")
    fun search(
        @RequestParam("date", defaultValue = "#{T(java.time.LocalDate).now()}") date: LocalDate,
    ): ApiResponse<ReservationDashboardResponse> {
        return ApiResponse.ok(
            ReservationDashboardResponse(
                date = date,
                workingTimes = (8 .. 20).toList(),
                reservations = reservationReadService.searchForDashboard(date)
                    .map { reservation ->
                        ReservationResponse(
                            staffId = reservation.staffId,
                            staffNickname = reservation.staffNickname,
                            reservationId = reservation.reservationId,
                            customerName = reservation.customerNameOrNull ?: "",
                            displayItemNames = reservation.displayItemName,
                            hour = reservation.reservedHour,
                            quarter = reservation.reservedQuarter,
                            totalQuarterTaken = reservation.totalQuarterTaken,
                            reservationSource = reservation.reservationSource,
                            reservationStatus = reservation.reservationStatus,
                        )
                    }
            )
        )
    }
}