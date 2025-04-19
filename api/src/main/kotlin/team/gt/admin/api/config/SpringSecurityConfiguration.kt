package team.gt.admin.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.context.DelegatingSecurityContextRepository
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository

@EnableWebSecurity
@Configuration
class SpringSecurityConfiguration {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationConfiguration(): AuthenticationConfiguration {
        return AuthenticationConfiguration()
    }

    @Bean
    fun customAuthenticationFilter(
        objectMapper: ObjectMapper,
        authenticationConfiguration: AuthenticationConfiguration,
        springSecurityAuthenticationSuccessHandler: SpringSecurityAuthenticationSuccessHandler,
        springSecurityAuthenticationFailureHandler: SpringSecurityAuthenticationFailureHandler,
    ): AbstractAuthenticationProcessingFilter {
        val springSecurityCustomAuthenticationFilter = SpringSecurityCustomAuthenticationFilter(
            objectMapper = objectMapper,
            authenticationManager = authenticationConfiguration.authenticationManager,
        )

        springSecurityCustomAuthenticationFilter.setAuthenticationSuccessHandler(springSecurityAuthenticationSuccessHandler)
        springSecurityCustomAuthenticationFilter.setAuthenticationFailureHandler(springSecurityAuthenticationFailureHandler)

        springSecurityCustomAuthenticationFilter.setSecurityContextRepository(
            DelegatingSecurityContextRepository(
                RequestAttributeSecurityContextRepository(),
                HttpSessionSecurityContextRepository(),
            )
        )

        return springSecurityCustomAuthenticationFilter
    }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        customAuthenticationFilter: AbstractAuthenticationProcessingFilter,
        springSecurityAuthenticationEntryPointHandler: SpringSecurityAuthenticationEntryPointHandler,
        springSecurityAccessDeniedHandler: SpringSecurityAccessDeniedHandler,
    ): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .headers { it.frameOptions { it.disable() } }
            .authorizeHttpRequests {
                it
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/api/v1/login", "/error", "/api/v1/init").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(springSecurityAuthenticationEntryPointHandler)
                it.accessDeniedHandler(springSecurityAccessDeniedHandler)
            }

        return http.build()
    }
}