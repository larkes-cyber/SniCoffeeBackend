package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class SearchForCoffeeDto(
    val symbols:String,
    val session:String
)