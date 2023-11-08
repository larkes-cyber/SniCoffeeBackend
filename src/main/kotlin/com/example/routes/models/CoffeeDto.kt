package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable()
data class CoffeeDto(
    val session:String,
    val id:String? = null,
    val photoSrc:String? = null,
    val includeBeans:Boolean = false,
    val includeMilk:Boolean = false,
    val categoryId:String,
    val categoryTitle:String,
    val subtitle:String,
    val description:String,
    val totalScore:Double = 0.0,
    val scoreCount:Int = 0,
    val price:Double
)