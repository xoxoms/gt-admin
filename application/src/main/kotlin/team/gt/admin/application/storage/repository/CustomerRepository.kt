package team.gt.admin.application.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import team.gt.admin.application.domain.customer.CustomerAndPerson
import team.gt.admin.application.storage.entity.CustomerEntity

interface CustomerRepository: JpaRepository<CustomerEntity, Long> {

    @Query(
        """
            select new team.gt.admin.application.domain.customer.CustomerAndPerson(
                c.id,
                p.id,
                p.name,
                c.visitSource
            )
            from CustomerEntity c
            inner join PersonEntity p on p.id = c.personId
            where c.id in (:customerIds)
        """
    )
    fun readCustomerAndPersonByCustomerIds(customerIds: List<Long>): List<CustomerAndPerson>
}