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
@Table(name = "crew")
class CrewEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "positionId")
    val positionId: Long,
    @Column(name = "personId")
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

    companion object {

        fun createNew(
            positionId: Long,
            personId: Long,
            nickname: String,
            mainPhone: String,
            regTs: LocalDateTime = LocalDateTime.now(),
            updTs: LocalDateTime = LocalDateTime.now(),
        ): CrewEntity {
            return CrewEntity(
                id = null,
                positionId = positionId,
                personId = personId,
                nickname = nickname,
                mainPhone = mainPhone,
                resigned = false,
                regTs = regTs,
                updTs = updTs,
            )
        }
    }
}