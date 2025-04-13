package team.gt.admin.application.storage.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDateTime
import org.springframework.data.annotation.LastModifiedDate

@Entity
@Table(
    name = "admin_user",
    uniqueConstraints = [
        UniqueConstraint(name = "u_idx_loginId", columnNames = ["loginId"]),
    ]
)
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "loginId", columnDefinition = "varchar(100) not null comment 'login ID'")
    val loginId: String,
    @Column(name = "password", columnDefinition = "varchar(300) not null comment '비밀번호'")
    val password: String,
    @Column(name = "name", columnDefinition = "varchar(100) not null comment '이름'")
    val name: String,
    @Column(name = "regTs", columnDefinition = "datetime(0) not null comment '생성일시'")
    val regTs: LocalDateTime,
    @LastModifiedDate
    @Column(name = "updTs", columnDefinition = "datetime(0) not null comment '마지막 수정일시'")
    val updTs: LocalDateTime,
) {

    companion object {

        fun createNew(
            loginId: String,
            password: String,
            name: String,
            regTs: LocalDateTime = LocalDateTime.now(),
            updTs: LocalDateTime = LocalDateTime.now(),
        ): UserEntity {
            return UserEntity(
                id = null,
                loginId = loginId,
                password = password,
                name = name,
                regTs = regTs,
                updTs = updTs,
            )
        }
    }
}