package com.example.data.model

data class UserEntity(
    var id:String?,
    val name:String,
    val photoSrc:String?,
    val number:String?,
    val login:String,
    var password:String,
    var favoriteCoffee:String = ""
)