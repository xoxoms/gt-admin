package team.gt.admin.application.exception

open class DomainException(
    override val message: String,
): Exception(message) {

}

class DomainNotFoundException: DomainException("존재하지 않습니다.")
class CanNotBlockStockException: DomainException("예약에 실패했습니다.")
class StaffSkillNotFoundException: DomainException("시술이 존재하지 않습니다.")
class CanNotCancelReservationException: DomainException("예약 취소할 수 있는 상태가 아닙니다.")
class CancelReservationFailException: DomainException("예약 취소에 실패했습니다.")