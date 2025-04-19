package team.gt.admin.api.controller

import java.time.LocalDate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.AvailableStockResponse
import team.gt.admin.application.service.BookingAvailabilityService

@RestController
class BookingAvailabilityController(
    private val bookingAvailabilityService: BookingAvailabilityService,
) {

    @GetMapping("/api/v1/available-stocks")
    fun searchBookable(
        @RequestParam staffId: Long,
        @RequestParam date: LocalDate,
        @RequestParam quarterTaken: Int
    ): ApiResponse<List<AvailableStockResponse>> {
        return ApiResponse.ok(
            bookingAvailabilityService.read(staffId, date, quarterTaken)
                .map {
                    AvailableStockResponse(
                        stockId = it.id,
                        hour = it.dateHour.hour,
                        quarter = it.quarter,
                    )
                }
        )
    }
}