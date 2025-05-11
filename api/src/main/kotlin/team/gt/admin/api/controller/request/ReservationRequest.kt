package team.gt.admin.api.controller.request

import java.time.LocalDate
import team.gt.admin.application.enums.ReservationItemType
import team.gt.admin.application.enums.ReservationSource

class ReservationRequest(
    val staffId: Long,
    val customerId: Long?,
    val source: ReservationSource,
    val date: LocalDate,
    val hour: Int,
    val quarter: Int,
    val itemTargets: List<ReservationItemTargetRequest>,
) {

    class ReservationItemTargetRequest(
        val itemType: ReservationItemType,
        val itemId: Long,
    )
}