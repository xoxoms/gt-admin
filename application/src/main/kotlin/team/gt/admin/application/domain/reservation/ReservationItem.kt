package team.gt.admin.application.domain.reservation

import java.time.LocalDateTime
import team.gt.admin.application.constants.JUST_CREATED_ID
import team.gt.admin.application.constants.SYSTEM
import team.gt.admin.application.enums.ReservationItemStatus
import team.gt.admin.application.enums.ReservationItemType


class ReservationItem(
    val id: Long,
    var reservationId: Long,
    val itemType: ReservationItemType,
    val itemId: Long,
    val itemName: String,
    val price: Int,
    val quarterTaken: Int,
    val status: ReservationItemStatus,
    val regTs: LocalDateTime,
    val updater: String,
    val updTs: LocalDateTime,
) {

    fun applyReservationId(reservationId: Long) {
        this.reservationId = reservationId
    }

    companion object {
        fun createNew(
            itemType: ReservationItemType,
            itemId: Long,
            itemName: String,
            price: Int,
            quarterTaken: Int,
            regTs: LocalDateTime = LocalDateTime.now(),
        ): ReservationItem {

            return ReservationItem(
                id = JUST_CREATED_ID,
                reservationId = JUST_CREATED_ID,
                itemType = itemType,
                itemId = itemId,
                itemName = itemName,
                price = price,
                quarterTaken = quarterTaken,
                status = ReservationItemStatus.RESERVED,
                regTs = regTs,
                updater = SYSTEM,
                updTs = regTs,
            )
        }
    }
}