package com.abbosidev.basic_auth.model

import com.abbosidev.basic_auth.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserInfo(
    private var username: String,
    private var password: String,
    private var authorities: List<GrantedAuthority>,
) : UserDetails {

    constructor(user: UserEntity) : this(
        username = user.username,
        password = user.password,
        authorities = ArrayList<GrantedAuthority>().apply {
            add(SimpleGrantedAuthority("ROLE_USER"))
        }
    )

    override fun getAuthorities() = authorities

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}