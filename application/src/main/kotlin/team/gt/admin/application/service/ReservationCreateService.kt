package team.gt.admin.application.service

import java.time.LocalDate
import org.springframework.stereotype.Service
import team.gt.admin.application.domain.reservation.Reservation
import team.gt.admin.application.domain.reservation.ReservationCreator
import team.gt.admin.application.domain.reservation.ReservationItem
import team.gt.admin.application.domain.reservation.ReservationItemTarget
import team.gt.admin.application.domain.staff.StaffSkillDetail
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
        val staffSkills = getStaffSkills(staffId, itemTargets)

        stockBlockProcessor.blockOrThrow(
            staffId = staffId,
            date = date,
            hour = hour,
            quarter = quarter,
            totalQuarterTaken = staffSkills.sumOf { it.quarterTaken },
        )

        return createReservation(
            staffId = staffId,
            customerId = customerId,
            source = source,
            date = date,
            hour = hour,
            quarter = quarter,
            staffSkills = staffSkills
        )
    }

    private fun createReservation(
        staffId: Long,
        customerId: Long?,
        source: ReservationSource,
        date: LocalDate,
        hour: Int,
        quarter: Int,
        staffSkills: List<StaffSkillDetail>,
    ): Long {
        val reservationItems = staffSkills
            .map {
                ReservationItem.createNew(
                    itemType = ReservationItemType.SKILL,
                    itemId = it.skillId,
                    itemName = it.skillName,
                    price = it.price,
                    quarterTaken = it.quarterTaken,
                )
            }

        val reservation = Reservation.createNew(
            staffId = staffId,
            customerId = customerId,
            reservationSource = source,
            reservedDate = date,
            reservedHour = hour,
            reservedQuarter = quarter,
            items = reservationItems
        )

        return reservationCreator.create(reservation)
    }

    private fun getStaffSkills(staffId: Long, itemTargets: List<ReservationItemTarget>): List<StaffSkillDetail> {
        val skillIds = itemTargets
            .filter { it.isSkill() }
            .map { it.itemId }

        return staffSkillReader.read(staffId, skillIds)
    }
}