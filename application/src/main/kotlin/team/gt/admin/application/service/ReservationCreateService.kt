package team.gt.admin.application.service

import java.time.LocalDate
import org.springframework.stereotype.Service
import team.gt.admin.application.domain.reservation.Reservation
import team.gt.admin.application.domain.reservation.ReservationCreator
import team.gt.admin.application.domain.reservation.ReservationItem
import team.gt.admin.application.domain.reservation.ReservationItemTarget
import team.gt.admin.application.domain.staff.StaffSkillReader
import team.gt.admin.application.domain.stock.StockBlockProcessor
import team.gt.admin.application.enums.ReservationItemType
import team.gt.admin.application.enums.ReservationSource

@Service
class ReservationCreateService(
    private val staffSkillReader: StaffSkillReader,
    private val stockBlockProcessor: StockBlockProcessor,
    private val reservationCreator: ReservationCreator,
) {

    fun create(
        staffId: Long,
        customerId: Long?,
        source: ReservationSource,
        date: LocalDate,
        hour: Int,
        quarter: Int,
        itemTargets: List<ReservationItemTarget>,
    ): Long {
        stockBlockProcessor.block(
            staffId,
            date,
            hour,
            quarter,

        )

        val reservation = Reservation.createNew(
            staffId = staffId,
            customerId = customerId,
            reservationSource = source,
            reservedDate = date,
            reservedHour = hour,
            reservedQuarter = quarter,
            items = makeReservationItems(staffId, itemTargets)
        )
        return reservationCreator.create(reservation)
    }

    private fun makeReservationItems(
        staffId: Long,
        itemTargets: List<ReservationItemTarget>,
    ): List<ReservationItem> {

        val skillIds = itemTargets
            .filter { it.isSkill() }
            .map { it.itemId }

        val staffSkills = staffSkillReader.read(staffId, skillIds)
        return staffSkills
            .map {
                ReservationItem.createNew(
                    itemType = ReservationItemType.SKILL,
                    itemId = it.skillId,
                    itemName = it.skillName,
                    price = it.price,
                    quarterTaken = it.quarterTaken,
                )
            }
    }
}