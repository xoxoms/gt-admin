package team.gt.admin.application.domain.person

import java.time.LocalDateTime
import org.springframework.stereotype.Component
import team.gt.admin.application.storage.entity.PersonEntity
import team.gt.admin.application.storage.repository.PersonRepository

@Component
class PersonCreator(
    private val personRepository: PersonRepository,
) {

    fun create(
        name: String,
        phone: String,
        address: String = "",
        regTs: LocalDateTime = LocalDateTime.now(),
        updTs: LocalDateTime = LocalDateTime.now(),
    ): Person {
        val saved = personRepository.save(
            PersonEntity.createNew(
                name = name,
                phone = phone,
                address = address,
                regTs = regTs,
                updTs = updTs,
            )
        )

        return Person.fromEntity(saved)
    }
}