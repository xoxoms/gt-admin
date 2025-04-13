package team.gt.admin.application.domain.customer

import java.time.LocalDateTime
import org.springframework.stereotype.Component
import team.gt.admin.application.enums.Sex
import team.gt.admin.application.enums.VisitSource
import team.gt.admin.application.storage.entity.CustomerEntity
import team.gt.admin.application.storage.repository.CustomerRepository

@Component
class CustomerCreator(
    private val customerRepository: CustomerRepository,
) {

    fun create(
        personId: Long,
        sex: Sex,
        visitSource: VisitSource,
        visitSourceId: Long?,
        regTs: LocalDateTime = LocalDateTime.now(),
        updTs: LocalDateTime = LocalDateTime.now(),
    ): Customer {
        val saved = customerRepository.save(
            CustomerEntity.createNew(
                personId = personId,
                sex = sex,
                visitSource = visitSource,
                visitSourceId = visitSourceId,
                regTs = regTs,
                updTs = updTs,
            )
        )

        return Customer.fromEntity(saved)
    }
}