package team.gt.admin.application.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = ["team.gt.admin.application.storage.repository"],
)
class JpaConfiguration {
}