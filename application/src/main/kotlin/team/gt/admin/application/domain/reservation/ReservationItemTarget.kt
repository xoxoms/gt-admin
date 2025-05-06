package team.gt.admin.application.domain.reservation

import team.gt.admin.application.enums.ReservationItemType

class ReservationItemTarget(
    val itemType: ReservationItemType,
    val itemId: Long,
)