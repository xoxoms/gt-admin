package team.gt.admin.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.domain.customer.CustomerCreator
import team.gt.admin.application.domain.person.PersonCreator
import team.gt.admin.application.enums.Sex
import team.gt.admin.application.enums.VisitSource

@Service
class CustomerCreateService(
    private val personCreator: PersonCreator,
    private val customerCreator: CustomerCreator,
) {

    @Transactional
    fun createCustomer(
        name: String,
        phone: String,
        sex: Sex,
        visitSource: VisitSource,
        visitSourceId: Long?,
    ): Long {
        val person = personCreator.create(
            name = name,
            phone = phone,
        )

        val customer = customerCreator.create(
            personId = person.id,
            sex = sex,
            visitSource = visitSource,
            visitSourceId = visitSourceId,
        )

        return customer.id
    }
}