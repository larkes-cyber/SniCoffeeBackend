package com.example.domain.model

import java.util.*

data class CoffeeKind(
    val id:String = UUID.randomUUID().toString(),
    val title:String
)