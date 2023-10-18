package com.example.domain.mapper

import com.example.data.model.UserEntity
import com.example.domain.model.User

fun User.toUserEntity():UserEntity{

    val favoriteCoffeeStr =if(favoriteCoffee.isNotEmpty()) favoriteCoffee.joinToString(";") else ""

    return UserEntity(
        id = id,
        name = name,
        photoSrc = photoSrc,
        number = number,
        login = login,
        password = password,
        favoriteCoffee = favoriteCoffeeStr
    )
}

fun UserEntity.toUser():User{
    return User(
        id = id,
        name = name,
        photoSrc = photoSrc,
        number = number,
        login = login,
        password = password,
        favoriteCoffee = if(favoriteCoffee.isNotEmpty()) favoriteCoffee.split(";") else emptyList()
    )
}