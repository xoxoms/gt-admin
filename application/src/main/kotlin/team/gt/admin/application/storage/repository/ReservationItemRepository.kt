package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.gt.admin.application.storage.entity.ReservationItemEntity

interface ReservationItemRepository: JpaRepository<ReservationItemEntity, Long>