package team.gt.admin.api.controller.request

import team.gt.admin.application.enums.Sex
import team.gt.admin.application.enums.VisitSource

class CustomerCreateRequest(
    val name: String,
    val phone: String,
    val sex: Sex,
    val visitSource: VisitSource,
    val visitSourceId: Long?,
) {
}