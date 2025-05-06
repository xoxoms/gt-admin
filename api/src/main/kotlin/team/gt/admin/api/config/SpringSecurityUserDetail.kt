package team.gt.admin.api.config

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SpringSecurityUserDetail(
    val loginId: String,
    @JvmField // 상속받는 interface의 getter와 코틀린이 자동생성하는 property의 getter가 동일하여 jvmField 선언함.
    val password: String,
): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return emptyList()
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.loginId
    }
}