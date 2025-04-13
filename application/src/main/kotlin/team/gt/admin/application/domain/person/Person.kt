package team.gt.admin.application.domain.person

import team.gt.admin.application.storage.entity.PersonEntity

class Person(
    val id: Long,
    val name: String,
    val phone: String,
    val address: String,
) {

    companion object {
        fun fromEntity(entity: PersonEntity): Person {
            return Person(
                id = entity.id!!,
                name = entity.name,
                phone = entity.phone,
                address = entity.address,
            )
        }
    }
}