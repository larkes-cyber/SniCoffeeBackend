package com.example.data.model

data class OrderEntity(
    var id:String?,
    var status:String,
    val userId:String,
    val orderPrice:Double,
    val productIds:String
)