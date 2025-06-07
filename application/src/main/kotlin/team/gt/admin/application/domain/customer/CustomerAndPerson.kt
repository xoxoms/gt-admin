package team.gt.admin.application.domain.customer

import team.gt.admin.application.enums.VisitSource

class CustomerAndPerson(
    val customerId: Long,
    val personId: Long,
    val personName: String,
    val visitSource: VisitSource,
) {
}