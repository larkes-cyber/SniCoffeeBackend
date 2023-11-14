package com.example.data.model

import java.util.*

data class CoffeeEntity(
    var id:String?,
    val includeBeans:Boolean,
    val includeMilk:Boolean,
    val categoryId:String,
    val categoryTitle:String,
    val subtitle:String,
    val description:String,
    val totalScore:Double,
    val scoreCount:Int,
    val price:Double
)