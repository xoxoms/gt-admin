package team.gt.admin.api.controller

import java.time.LocalDate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.application.service.BookingAvailabilityService

@RestController
class BookingAvailabilityController(
    private val bookingAvailabilityService: BookingAvailabilityService,
) {

    @GetMapping("/api/v1/bookable")
    fun searchBookable(
        @RequestParam staffId: Long,
        @RequestParam date: LocalDate,
    ) {
    }
}