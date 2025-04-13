package team.gt.admin.application.domain.customer

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import team.gt.admin.application.storage.repository.CustomerRepository

@Component
class CustomerReader(
    private val customerRepository: CustomerRepository,
) {

    fun search(
        page: Int,
        size: Int,
    ): Page<Customer> {
        return customerRepository.findAll(PageRequest.of(page, size, Sort.by("id")))
            .map(Customer::fromEntity)
    }
}