package com.example.data.model

data class UserEntity(
    val id:String?,
    val name:String,
    val photoSrc:String?,
    val number:String,
    val login:String,
    val passwordHash:String,
    val favoriteCoffee:String
)