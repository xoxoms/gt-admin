package team.gt.admin.application.parser.staff

import team.gt.admin.application.domain.staff.Staff
import team.gt.admin.application.storage.entity.StaffEntity

object StaffParser {

    fun fromDomain(domain: Staff): StaffEntity {

        return StaffEntity(
            id = domain.id,
            positionId = domain.positionId,
            personId = domain.personId,
            nickname = domain.nickname,
            mainPhone = domain.mainPhone,
            resigned = domain.resigned,
            regTs = domain.regTs,
            updTs = domain.updTs,
        )
    }

    fun toDomain(entity: StaffEntity): Staff {

        return Staff(
            id = entity.id!!,
            positionId = entity.positionId,
            personId = entity.personId,
            nickname = entity.nickname,
            mainPhone = entity.mainPhone,
            resigned = entity.resigned,
            regTs = entity.regTs,
            updTs = entity.updTs,
        )
    }
}