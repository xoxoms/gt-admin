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
@Table(name = "skill")
class SkillEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "name", columnDefinition = "varchar(100) not null comment '직책명'")
    val name: String,
    @Column(name = "defaultPrice", columnDefinition = "int not null comment '기본 가격'")
    val defaultPrice: Int,
    @Column(name = "defaultQuarterTaken", columnDefinition = "int not null comment '기본 소요시간(15분 기준)'")
    val defaultQuarterTaken: Int,
    @Column(name = "deleted", columnDefinition = "bit(1) not null comment '삭제여부'")
    val deleted: Boolean,
    @Column(name = "creator", columnDefinition = "varchar(100) not null comment '만든이'")
    val creator: String,
    @Column(name = "regTs", columnDefinition = "datetime(0) not null comment '생성일시'")
    val regTs: LocalDateTime,
    @Column(name = "updater", columnDefinition = "varchar(100) not null comment '수정자'")
    val updater: String,
    @LastModifiedDate
    @Column(name = "updTs", columnDefinition = "datetime(0) not null comment '마지막 수정일시'")
    val updTs: LocalDateTime,
) {

}