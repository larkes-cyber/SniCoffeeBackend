package com.example.domain.model

import java.util.*

data class Coffee(
    val id:String? = null,
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