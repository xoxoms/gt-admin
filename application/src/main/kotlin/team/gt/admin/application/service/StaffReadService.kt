package team.gt.admin.application.service

import org.springframework.stereotype.Service
import team.gt.admin.application.domain.staff.Staff
import team.gt.admin.application.domain.staff.StaffDetailView
import team.gt.admin.application.domain.staff.StaffReader
import team.gt.admin.application.domain.person.Person
import team.gt.admin.application.domain.person.PersonReader
import team.gt.admin.application.domain.position.Position
import team.gt.admin.application.domain.position.PositionReader

@Service
class StaffReadService(
    private val positionReader: PositionReader,
    private val staffReader: StaffReader,
    private val personReader: PersonReader,
) {

    fun readAll(): List<StaffDetailView> {
        val positions = positionReader.readAll()
        val staffs = staffReader.readAllWorkingStaffs()
        val persons = staffs.map { it.personId }
            .chunked(128)
            .flatMap { ids ->
                personReader.readByPersonIds(ids)
            }

        return associateStaffView(positions, staffs, persons)
    }

    private fun associateStaffView(
        positions: List<Position>,
        staffs: List<Staff>,
        persons: List<Person>,
    ): List<StaffDetailView> {
        val positionIdPositionMap = positions.associateBy { it.id }
        val personIdPersonMap = persons.associateBy { it.id }
        return staffs.map { staff ->
            val position = positionIdPositionMap[staff.positionId]
            val person = personIdPersonMap[staff.personId]
            val default = "-"

            StaffDetailView(
                staffId = staff.id,
                positionId = staff.positionId,
                personId = staff.positionId,
                privatePhone = person?.phone ?: default,
                privateAddress = person?.address ?: default,
                nickname = staff.nickname,
                mainPhone = staff.mainPhone,
                position = position?.name ?: default,
            )
        }
    }
}