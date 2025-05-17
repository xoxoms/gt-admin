package team.gt.admin.application.exception

open class DomainException(
    override val message: String,
): Exception(message) {

}

class DomainNotFoundException: DomainException("존재하지 않습니다.")

class CanNotBlockStockException: DomainException("예약에 실패했습니다.")