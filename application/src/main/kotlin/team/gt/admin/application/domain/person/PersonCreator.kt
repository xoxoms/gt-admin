package team.gt.admin.application.domain.person

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.person.PersonParser
import team.gt.admin.application.storage.repository.PersonRepository

@Component
class PersonCreator(
    private val personRepository: PersonRepository,
) {

    fun create(person: Person): Person {
        val saved = personRepository.save(PersonParser.fromDomain(person))
        return PersonParser.toDomain(saved)
    }
}