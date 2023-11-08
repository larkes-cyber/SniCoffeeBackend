package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class SelectCategoryDto(
    val session:String,
    val category:String
)