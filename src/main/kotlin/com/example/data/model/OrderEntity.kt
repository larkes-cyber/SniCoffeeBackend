package com.example.data.model

data class OrderEntity(
    val id:String,
    val status:String,
    val userId:String,
    val orderPrice:Double,
    val productIds:String
)