package team.gt.admin.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.gt.admin.application.domain.person.Person
import team.gt.admin.application.domain.person.PersonCreator
import team.gt.admin.application.domain.staff.Staff
import team.gt.admin.application.domain.staff.StaffCreator
import team.gt.admin.application.domain.staff.StaffSkillMapper
import team.gt.admin.application.domain.staff.StaffSkillMapping
import team.gt.admin.application.domain.staff.StaffSkillMappingData

@Service
class StaffCreateService(
    private val personCreator: PersonCreator,
    private val staffCreator: StaffCreator,
    private val staffSkillMapper: StaffSkillMapper,
) {

    @Transactional
    fun createStaff(
        name: String,
        privatePhone: String,
        privateAddress: String,
        nickname: String,
        mainPhone: String,
        positionId: Long,
        staffSkillMappingData: List<StaffSkillMappingData>,
    ): Long {

        val person = personCreator.create(
            Person.createNew(
                name = name,
                phone = privatePhone,
                address = privateAddress,
            )
        )

        val staff = staffCreator.create(
            Staff.createNew(
                positionId = positionId,
                personId = person.id,
                nickname = nickname,
                mainPhone = mainPhone,
            )
        )

        staffSkillMapper.create(
            staffSkillMappingData.map {
                StaffSkillMapping.createNew(
                    staffId = staff.id,
                    skillId = it.skillId,
                    price = it.price,
                    quarterTaken = it.quarterTaken,
                )
            }
        )

        return staff.id
    }
}