package team.gt.admin.api.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.ReservationRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.application.domain.reservation.ReservationItemTarget
import team.gt.admin.application.service.ReservationCreateService

@RestController
class ReservationController(
    private val reservationCreateService: ReservationCreateService,
) {

    @PostMapping("/api/v1/reservations")
    fun createReservation(
        @RequestBody request: ReservationRequest,
    ): ApiResponse<Long> {
        val reservationId = reservationCreateService.create(
            staffId = request.staffId,
            customerId = request.customerId,
            source = request.source,
            date = request.date,
            hour = request.hour,
            quarter = request.quarter,
            itemTargets = request
                .itemTargets
                .map { ReservationItemTarget(it.itemType, it.itemId) },
        )

        return ApiResponse.ok(reservationId)
    }
}