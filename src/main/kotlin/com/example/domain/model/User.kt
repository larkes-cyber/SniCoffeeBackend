package com.example.domain.model

import java.util.*

data class User(
    val id:String? = null,
    val name:String,
    val photoSrc:String? = null,
    val number:String,
    val login:String,
    val passwordHash:String,
    val favoriteCoffee:List<String>
)