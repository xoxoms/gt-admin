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
import team.gt.admin.application.enums.Sex
import team.gt.admin.application.enums.VisitSource

@Entity
@Table(name = "customer")
class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "personId", columnDefinition = "bigint(11) not null comment 'FK: person.id'")
    val personId: Long,
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", columnDefinition = "varchar(10) not null comment '성별'")
    val sex: Sex,
    @Enumerated(EnumType.STRING)
    @Column(name = "visitSource", columnDefinition = "varchar(20) not null comment '방문경로'")
    val visitSource: VisitSource,
    @Column(name = "visitSourceId", columnDefinition = "bigint(11) null comment 'FK: {visitSource}.id'")
    val visitSourceId: Long?,
    @Column(name = "regTs", columnDefinition = "datetime(0) not null comment '생성일시'")
    val regTs: LocalDateTime,
    @LastModifiedDate
    @Column(name = "updTs", columnDefinition = "datetime(0) not null comment '마지막 수정일시'")
    val updTs: LocalDateTime,
) {

    companion object {

        fun createNew(
            personId: Long,
            sex: Sex,
            visitSource: VisitSource,
            visitSourceId: Long?,
            regTs: LocalDateTime = LocalDateTime.now(),
            updTs: LocalDateTime = LocalDateTime.now(),
        ): CustomerEntity {
            return CustomerEntity(
                id = null,
                personId = personId,
                sex = sex,
                visitSource = visitSource,
                visitSourceId = visitSourceId,
                regTs = regTs,
                updTs = updTs,
            )
        }
    }
}