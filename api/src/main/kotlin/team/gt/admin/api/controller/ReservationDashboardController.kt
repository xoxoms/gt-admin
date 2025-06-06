package team.gt.admin.api.controller

import java.time.LocalDate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.gt.admin.api.controller.response.ApiResponse
import team.gt.admin.api.controller.response.ReservationDashboardResponse
import team.gt.admin.application.enums.VisitSource
import team.gt.admin.application.service.ReservationReadService

@RestController
class ReservationDashboardController(
    private val reservationReadService: ReservationReadService,
) {

    @GetMapping("/api/v1/reservation-dashboard")
    fun getReservationDashboard(
        @RequestParam("date", defaultValue = "#{T(java.time.LocalDate).now()}") date: LocalDate,
    ): ApiResponse<ReservationDashboardResponse> {
        return ApiResponse.ok(
            ReservationDashboardResponse(
                date = date,
                workingTimes = (8..20).toList(),
                perStaffs = mutableListOf(
                    ReservationDashboardResponse.PerStaff(
                        staffId = 0L,
                        staffNickname = "첫방문A",
                        bookings = mutableListOf()
                    ),
                    ReservationDashboardResponse.PerStaff(
                        staffId = 0L,
                        staffNickname = "첫방문B",
                        bookings = mutableListOf()
                    ),
                    ReservationDashboardResponse.PerStaff(
                        staffId = 1L,
                        staffNickname = "대표 원장",
                        bookings = mutableListOf(
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 1L,
                                customerName = "구동매",
                                displayItemNames = "헝클어진 사무라이 머리 컷",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 3,
                                booked = true,
                                source = VisitSource.WorkIn,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 2L,
                                customerName = "주덕배",
                                displayItemNames = "삭발 염색",
                                hour = 9,
                                quarter = 2,
                                totalQuarterTaken = 5,
                                booked = true,
                                source = VisitSource.SNS,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 3L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 4L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 5L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 6L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 7L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                        )
                    ),
                    ReservationDashboardResponse.PerStaff(
                        staffId = 2L,
                        staffNickname = "부원장",
                        bookings = mutableListOf(
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 1L,
                                customerName = "구동매",
                                displayItemNames = "헝클어진 사무라이 머리 컷",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 3,
                                booked = true,
                                source = VisitSource.WorkIn,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 2L,
                                customerName = "주덕배",
                                displayItemNames = "삭발 염색",
                                hour = 9,
                                quarter = 2,
                                totalQuarterTaken = 5,
                                booked = true,
                                source = VisitSource.SNS,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 3L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 4L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 5L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 6L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                            ReservationDashboardResponse.BookedItem(
                                bookingItemId = 7L,
                                customerName = "구준표",
                                displayItemNames = "베이비펌",
                                hour = 9,
                                quarter = 1,
                                totalQuarterTaken = 2,
                                booked = true,
                                source = VisitSource.Naver,
                            ),
                        )
                    )
                ),
            )
        )
    }
}