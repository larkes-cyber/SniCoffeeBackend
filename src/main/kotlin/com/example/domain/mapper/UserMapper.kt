package com.example.domain.mapper

import com.example.data.model.UserEntity
import com.example.domain.model.User

fun User.toUserEntity():UserEntity{

    val favoriteCoffeeStr = favoriteCoffee.joinToString(";")

    return UserEntity(
        id = id,
        name = name,
        photoSrc = photoSrc,
        number = number,
        login = login,
        passwordHash = passwordHash,
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
        passwordHash = passwordHash,
        favoriteCoffee = favoriteCoffee.split(";")
    )
}