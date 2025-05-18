package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.ReservationEntity

interface ReservationRepository: JpaRepository<ReservationEntity, Long> {

}