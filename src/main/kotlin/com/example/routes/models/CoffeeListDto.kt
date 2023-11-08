package com.example.routes.models

import com.example.domain.model.Coffee
import kotlinx.serialization.Serializable

@Serializable
data class CoffeeListDto(
    val coffeeList:List<CoffeeDto>
)