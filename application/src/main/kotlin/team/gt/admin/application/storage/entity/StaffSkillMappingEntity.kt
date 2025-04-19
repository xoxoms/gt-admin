package team.gt.admin.application.storage.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.springframework.data.annotation.LastModifiedDate

@Entity
@Table(name = "staff_skill_mapping")
class StaffSkillMappingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "staffId", columnDefinition = "bigint(11) not null comment 'FK: staff.id'")
    val staffId: Long,
    @Column(name = "skillId", columnDefinition = "bigint(11) not null comment 'FK: skill.id'")
    val skillId: Long,
    @Column(name = "price", columnDefinition = "int not null comment '시술가격'")
    val price: Int,
    @Column(name = "quarterTaken", columnDefinition = "int not null comment '소요시간(15분 기준)'")
    val quarterTaken: Int,
    @Column(name = "deleted", columnDefinition = "bit(1) not null comment '삭제여부'")
    val deleted: Boolean,
    @Column(name = "regTs", columnDefinition = "datetime(0) not null comment '생성일시'")
    val regTs: LocalDateTime,
    @LastModifiedDate
    @Column(name = "updTs", columnDefinition = "datetime(0) not null comment '마지막 수정일시'")
    val updTs: LocalDateTime,
) {
}