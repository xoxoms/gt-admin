package team.gt.admin.application.parser.person

import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.domain.person.Person
import team.gt.admin.application.storage.entity.PersonEntity

object PersonParser {

    fun fromDomain(domain: Person): PersonEntity {

        return PersonEntity(
            id = if (domain.id == JUST_CREATED_ID) null else domain.id,
            name = domain.name,
            phone = domain.phone,
            address = domain.address,
            regTs = domain.regTs,
            updTs = domain.updTs,
        )
    }



    fun toDomain(entity: PersonEntity): Person {

        return Person(
            id = entity.id!!,
            name = entity.name,
            phone = entity.phone,
            address = entity.address,
            regTs = entity.regTs,
            updTs = entity.updTs,
        )
    }
}