package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id:String? = null,
    val name:String,
    val number:String? = null,
    val login:String,
    val password:String,
    val favoriteCoffee:String
)