package team.gt.admin.application.domain.reservation

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.storage.repository.ReservationRepository

@Component
class ReservationUpdater(
    private val reservationRepository: ReservationRepository,
) {

    @Transactional
    fun cancel(reservationId: Long): Boolean {
        return reservationRepository.cancel(reservationId) == 1
    }
}