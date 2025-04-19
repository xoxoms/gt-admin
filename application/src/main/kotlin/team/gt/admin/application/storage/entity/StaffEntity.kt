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
@Table(name = "staff")
class StaffEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "positionId", columnDefinition = "bigint(11) not null comment 'FK: position.id'")
    val positionId: Long,
    @Column(name = "personId", columnDefinition = "bigint(11) not null comment 'FK: person.id'")
    val personId: Long,
    @Column(name = "nickname", columnDefinition = "varchar(100) not null comment '닉네임'")
    val nickname: String,
    @Column(name = "mainPhone", columnDefinition = "varchar(100) not null comment '업무용 전화번호'")
    val mainPhone: String,
    @Column(name = "resigned", columnDefinition = "bit(1) not null comment '퇴사여부'")
    val resigned: Boolean,
    @Column(name = "regTs", columnDefinition = "datetime(0) not null comment '생성일시'")
    val regTs: LocalDateTime,
    @LastModifiedDate
    @Column(name = "updTs", columnDefinition = "datetime(0) not null comment '마지막 수정일시'")
    val updTs: LocalDateTime,
) {
}