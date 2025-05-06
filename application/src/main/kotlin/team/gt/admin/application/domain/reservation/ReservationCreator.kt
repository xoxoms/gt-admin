package team.gt.admin.application.domain.reservation

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.reservation.ReservationParser
import team.gt.admin.application.storage.repository.ReservationItemRepository
import team.gt.admin.application.storage.repository.ReservationRepository

@Component
class ReservationCreator(
    private val reservationRepository: ReservationRepository,
    private val reservationItemRepository: ReservationItemRepository,
) {

    fun create(reservation: Reservation): Long {
        val reservationEntity = ReservationParser.fromDomain(reservation)
        val savedReservationEntity = reservationRepository.save(reservationEntity)
        reservationItemRepository.saveAll(
            reservation.items
                .map {
                    it.applyReservationId(savedReservationEntity.id!!)
                    ReservationParser.fromDomain(it)
                }
        )

        return reservationEntity.id!!
    }
}