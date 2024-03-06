package com.abbosidev.basic_auth.entity

import jakarta.persistence.*

@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var firstname: String,
    var lastname: String,
    var username: String,
    var password: String
)