package team.gt.admin.application.service

import java.time.LocalDate
import org.springframework.stereotype.Service
import team.gt.admin.application.domain.reservation.ReservationReader
import team.gt.admin.application.parser.reservation.ReservationListView

@Service
class ReservationReadService(
    private val reservationReader: ReservationReader,
) {

    fun search(
        staffId: Long? = null,
        startDate: LocalDate? = null,
        endDate: LocalDate? = null,
        size: Int? = null,
        lastCursor: Long? = null,
    ): List<ReservationListView> {
        return reservationReader.search(
            staffId = staffId,
            startDate = startDate ?: DEFAULT_SEARCH_START_DATE,
            endDate = endDate ?: DEFAULT_SEARCH_END_DATE,
            size = size ?: DEFAULT_MAX_SEARCH_SIZE,
            lastCursor = lastCursor ?: DEFAULT_LAST_CURSOR,
        )
    }

    fun searchForDashboard(date: LocalDate): List<ReservationListView> {
        return search(
            startDate = date,
            endDate = date,
        )
    }

    companion object {
        private val DEFAULT_SEARCH_START_DATE = LocalDate.of(2024, 1, 1)
        private val DEFAULT_SEARCH_END_DATE = LocalDate.of(2099, 1, 1)
        private const val DEFAULT_MAX_SEARCH_SIZE = 10_000
        private const val DEFAULT_LAST_CURSOR = 0L
    }
}