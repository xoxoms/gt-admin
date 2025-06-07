package team.gt.admin.application.storage.repository

import java.time.LocalDateTime
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.storage.entity.ReservationEntity

@Repository
interface ReservationRepository: JpaRepository<ReservationEntity, Long> {
    
    fun findByIdGreaterThanAndReservedVisitDateTimeBetween(
        cursor: Long,
        searchStartDateTimeInclusive: LocalDateTime, 
        searchEndDateTimeInclusive: LocalDateTime,
        page: Pageable
    ): List<ReservationEntity>
    
    @Query(
        nativeQuery = false,
        value = """
            update ReservationEntity r 
            set r.status = team.gt.admin.application.enums.ReservationStatus.CANCELED 
            where r.id = :reservationId 
                and r.status = team.gt.admin.application.enums.ReservationStatus.NORMAL
        """
    )
    @Modifying
    @Transactional(propagation = Propagation.MANDATORY)
    fun cancel(reservationId: Long): Int
}