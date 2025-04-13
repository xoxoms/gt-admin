package team.gt.admin.application.domain.customer

import team.gt.admin.application.enums.Sex
import team.gt.admin.application.enums.VisitSource
import team.gt.admin.application.storage.entity.CustomerEntity

class Customer(
    val id: Long,
    val personId: Long,
    val sex: Sex,
    val visitSource: VisitSource,
    val visitSourceId: Long?,
) {

    companion object {

        fun fromEntity(entity: CustomerEntity): Customer {
            return Customer(
                id = entity.id!!,
                personId = entity.personId,
                sex = entity.sex,
                visitSource = entity.visitSource,
                visitSourceId = entity.visitSourceId,
            )
        }
    }
}