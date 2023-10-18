package com.example.domain.model

import java.util.*

data class Order(
    val id:String? = null,
    val status:String,
    val userId:String,
    val orderPrice:Double,
    val productIds:List<String>
)