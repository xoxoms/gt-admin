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
        staffId: Long?,
        date: LocalDate?,
        hour: Int?,
        size: Int,
        lastCursor: Long?,
    ): List<ReservationListView> {
        return reservationReader.search(staffId, date, hour, size, lastCursor)
    }
}