package team.gt.admin.application.storage.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.data.annotation.LastModifiedDate

@Entity
@Table(name = "staff_schedule_stock")
class StaffScheduleStockEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "staffId", columnDefinition = "bigint(11) not null comment 'FK: staff.id'")
    val staffId: Long,
    @Column(name = "stockDate", columnDefinition = "date not null comment '날짜'")
    val stockDate: LocalDate,
    @Column(name = "stockHour", columnDefinition = "int not null comment '시간'")
    val stockHour: Int,
    @Column(name = "quarter", columnDefinition = "int not null comment '쿼터'")
    val quarter: Int,
    @Column(name = "available", columnDefinition = "bit(1) not null comment '예약가능 여부'")
    val available: Boolean,
    @Column(name = "regTs", columnDefinition = "datetime(0) not null comment '생성일시'")
    val regTs: LocalDateTime,
    @LastModifiedDate
    @Column(name = "updTs", columnDefinition = "datetime(0) not null comment '마지막 수정일시'")
    val updTs: LocalDateTime,
) {
}