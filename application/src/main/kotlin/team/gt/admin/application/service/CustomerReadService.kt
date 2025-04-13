package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.customer.Customer
import team.gt.admin.application.domain.customer.CustomerListView
import team.gt.admin.application.domain.customer.CustomerReader
import team.gt.admin.application.domain.person.Person
import team.gt.admin.application.domain.person.PersonReader
import team.gt.admin.application.utils.PageImpl

@Service
class CustomerReadService(
    private val customerReader: CustomerReader,
    private val personReader: PersonReader,
) {

    fun search(
        page: Int,
        size: Int,
    ): PageImpl<CustomerListView> {
        val customers = customerReader.search(page, size)
        val persons = customers.map { it.personId }
            .chunked(128)
            .flatMap { ids ->
                personReader.readByPersonIds(ids)
            }

        val result = associateCustomerListView(customers.content, persons)
        return PageImpl(
            items = result,
            totalCounts = customers.totalElements,
            totalPages = customers.totalPages,
            page = page,
            size = size,
        )
    }

    private fun associateCustomerListView(
        customers: List<Customer>,
        persons: List<Person>,
    ): List<CustomerListView> {
        val personIdPersonMap = persons.associateBy { it.id }

        return customers.map { customer ->
            val person = personIdPersonMap[customer.personId]
            val default = "-"

            CustomerListView(
                customerId = customer.id,
                personId = customer.personId,
                name = person?.name ?: default
            )
        }
    }
}