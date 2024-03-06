package com.abbosidev.basic_auth.config

import com.abbosidev.basic_auth.service.UserAuthService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userAuthService: UserAuthService) {

    @Bean
    fun userDetailsService(): UserDetailsService = userAuthService

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .csrf(AbstractHttpConfigurer<*, *>::disable)
        .authorizeHttpRequests { request ->
            request
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        }
        .httpBasic(Customizer.withDefaults())
        .build()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider() = DaoAuthenticationProvider().apply {
        setUserDetailsService(userDetailsService())
        setPasswordEncoder(passwordEncoder())
    }

}