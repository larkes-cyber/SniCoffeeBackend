package com.example.domain.model

import java.util.*

data class User(
    val id:String? = null,
    val name:String,
    val number:String? = null,
    val login:String,
    val password:String,
    val favoriteCoffee:List<String> = listOf()
)