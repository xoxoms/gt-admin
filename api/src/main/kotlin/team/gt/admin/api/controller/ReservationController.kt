package team.gt.admin.api.controller

import java.time.LocalDate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.request.ReservationRequest
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.ReservationListViewResponse
import team.gt.admin.application.domain.reservation.ReservationItemTarget
import team.gt.admin.application.service.ReservationCreateService
import team.gt.admin.application.service.ReservationReadService
import team.gt.admin.application.service.ReservationUpdateService

@RestController
class ReservationController(
    private val reservationReadService: ReservationReadService,
    private val reservationCreateService: ReservationCreateService,
    private val reservationUpdateService: ReservationUpdateService,
) {

    @GetMapping("/api/v1/reservations")
    fun searchReservations(
        @RequestParam(required = false) staffId: Long?,
        @RequestParam(required = false) startDate: LocalDate?,
        @RequestParam(required = false) endDate: LocalDate?,
        @RequestParam(required = false) size: Int,
        @RequestParam(required = false) lastCursor: Long?,
    ): ApiResponse<List<ReservationListViewResponse>> {
        return ApiResponse.ok(
            reservationReadService
                .search(staffId, startDate, endDate, size, lastCursor)
                .map(ReservationListViewResponse::of)
        )
    }

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

    @PostMapping("/api/v1/reservations/{reservationId}/cancel")
    fun cancel(@PathVariable reservationId: Long): ApiResponse<Unit> {
        reservationUpdateService.cancel(reservationId)
        return ApiResponse.ok(Unit)
    }
}