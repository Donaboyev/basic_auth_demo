package com.abbosidev.basic_auth.service

import com.abbosidev.basic_auth.entity.UserEntity
import com.abbosidev.basic_auth.model.UserInfo
import com.abbosidev.basic_auth.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserAuthService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
        return UserInfo(user)
    }
}