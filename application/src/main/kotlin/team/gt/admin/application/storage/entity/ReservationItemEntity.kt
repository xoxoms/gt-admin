package team.gt.admin.application.storage.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.springframework.data.annotation.LastModifiedDate
import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.domain.reservation.Reservation
import team.gt.admin.application.enums.ReservationItemStatus
import team.gt.admin.application.enums.ReservationItemType

@Entity
@Table(name = "reservation_item")
class ReservationItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "reservationId", columnDefinition = "bigint(11) not null comment 'FK: reservation.id'")
    val reservationId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "itemType", columnDefinition = "varchar(20) not null comment '예약항목 유형'")
    val itemType: ReservationItemType,
    @Column(name = "itemId", columnDefinition = "bigint(11) not null comment '예약항목 ID'")
    val itemId: Long,
    @Column(name = "itemName", columnDefinition = "varchar(100) not null comment '예약항목 이름'")
    val itemName: String,
    @Column(name = "price", columnDefinition = "int not null comment '가격'")
    val price: Int,
    @Column(name = "quarterTaken", columnDefinition = "int not null comment '소요시간(15분 기준)'")
    val quarterTaken: Int,
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(20) not null comment '상태'")
    val status: ReservationItemStatus,
    @Column(name = "regTs", columnDefinition = "datetime(0) not null comment '생성일시'")
    val regTs: LocalDateTime,
    @Column(name = "updater", columnDefinition = "varchar(100) not null comment '수정자'")
    val updater: String,
    @LastModifiedDate
    @Column(name = "updTs", columnDefinition = "datetime(0) not null comment '마지막 수정일시'")
    val updTs: LocalDateTime,
) {
    companion object {

        fun createNew(
            reservationId: Long,
            itemType: ReservationItemType,
            itemId: Long,
            itemName: String,
            price: Int,
            quarterTaken: Int,
            status: ReservationItemStatus,
        ): ReservationItemEntity {

            return ReservationItemEntity(
                id = JUST_CREATED_ID,
                reservationId = reservationId,
                itemType = itemType,
                itemId = itemId,
                itemName = itemName,
                price = price,
                quarterTaken = quarterTaken,
                status = status,
                regTs = LocalDateTime.now(),
                updater = "SYSTEM",
                updTs = LocalDateTime.now(),
            )
        }
    }
}