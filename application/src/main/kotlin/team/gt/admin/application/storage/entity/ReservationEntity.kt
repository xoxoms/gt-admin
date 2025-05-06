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
import team.gt.admin.application.enums.ReservationSource
import team.gt.admin.application.enums.ReservationStatus

@Entity
@Table(name = "reservation")
class ReservationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "staffId", columnDefinition = "bigint(11) not null comment 'FK: staff.id'")
    val staffId: Long,
    @Column(name = "customerId", columnDefinition = "bigint(11) null comment 'FK: customer.id'")
    val customerId: Long?,
    @Enumerated(EnumType.STRING)
    @Column(name = "reservationSource", columnDefinition = "varchar(20) not null comment '예약출처'")
    val reservationSource: ReservationSource,
    @Column(name = "reservedVisitDateTime", columnDefinition = "datetime(0) not null comment '예약방문일시'")
    val reservedVisitDateTime: LocalDateTime,
    @Column(name = "actualVisitDateTime", columnDefinition = "datetime(0) null comment '실제방문일시'")
    val actualVisitDateTime: LocalDateTime?,
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "varchar(20) not null comment '상태'")
    val status: ReservationStatus,
    @Column(name = "regTs", columnDefinition = "datetime(0) not null comment '생성일시'")
    val regTs: LocalDateTime,
    @Column(name = "updater", columnDefinition = "varchar(100) not null comment '수정자'")
    val updater: String,
    @LastModifiedDate
    @Column(name = "updTs", columnDefinition = "datetime(0) not null comment '마지막 수정일시'")
    val updTs: LocalDateTime,
) {

}