package team.gt.admin.api.controller

import java.time.LocalDate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.application.service.ReservationReadService

@RestController
class ReservationDashboardController(
    private val reservationReadService: ReservationReadService,
) {

    @GetMapping("/api/v1/reservation-dashboard")
    fun getReservationDashboard(
        @RequestParam date: LocalDate,
    ) {

    }
}