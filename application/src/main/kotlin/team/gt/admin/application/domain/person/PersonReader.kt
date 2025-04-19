package team.gt.admin.application.domain.person

import org.springframework.stereotype.Component
import team.gt.admin.application.parser.person.PersonParser
import team.gt.admin.application.storage.repository.PersonRepository

@Component
class PersonReader(
    private val personRepository: PersonRepository,
) {

    fun readByPersonIds(personIds: List<Long>): List<Person> {
        return personRepository.findByIdIn(personIds)
            .map(PersonParser::toDomain)
    }
}