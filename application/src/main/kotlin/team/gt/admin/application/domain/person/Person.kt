package team.gt.admin.application.domain.person

import java.time.LocalDateTime
import team.gt.admin.application.constants.JUST_CREATED_ID

class Person(
    val id: Long,
    val name: String,
    val phone: String,
    val address: String,
    val regTs: LocalDateTime,
    val updTs: LocalDateTime,
) {

    companion object {

        fun createNew(
            name: String,
            phone: String,
            address: String,
            regTs: LocalDateTime = LocalDateTime.now(),
            updTs: LocalDateTime = LocalDateTime.now(),
        ): Person {
            return Person(
                id = JUST_CREATED_ID,
                name = name,
                phone = phone,
                address = address,
                regTs = regTs,
                updTs = updTs,
            )
        }
    }
}