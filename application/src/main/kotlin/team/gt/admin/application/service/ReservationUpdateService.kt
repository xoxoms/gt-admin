package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.reservation.ReservationReader
import team.gt.admin.application.domain.reservation.ReservationUpdater
import team.gt.admin.application.exception.CanNotCancelReservationException
import team.gt.admin.application.exception.CancelReservationFailException

@Service
class ReservationUpdateService(
    private val reservationReader: ReservationReader,
    private val reservationUpdater: ReservationUpdater,
) {

    fun cancel(reservationId: Long) {
        val reservation = reservationReader.readByIdOrThrow(reservationId)
        if (reservation.canNotCancel()) {
            throw CanNotCancelReservationException()
        }

        val result = reservationUpdater.cancel(reservationId)
        if (result == false) {
            throw CancelReservationFailException()
        }
    }
}