package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteCoffeeDto(
    val session:String,
    val coffeeId:String
)