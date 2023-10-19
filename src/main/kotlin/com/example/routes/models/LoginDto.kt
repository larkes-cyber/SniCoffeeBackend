package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val login:String,
    val password:String
)