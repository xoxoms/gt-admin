package team.gt.admin.application.domain.reservation

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.parser.reservation.ReservationParser
import team.gt.admin.application.storage.repository.ReservationItemRepository
import team.gt.admin.application.storage.repository.ReservationRepository

@Component
class ReservationCreator(
    private val reservationRepository: ReservationRepository,
    private val reservationItemRepository: ReservationItemRepository,
) {

    @Transactional
    fun create(reservation: Reservation): Long {
        val savedReservationEntity = reservationRepository.save(ReservationParser.fromDomain(reservation))
        reservationItemRepository.saveAll(
            reservation.items
                .map {
                    it.applyReservationId(savedReservationEntity.id!!)
                    ReservationParser.fromDomain(it)
                }
        )

        return savedReservationEntity.id!!
    }
}