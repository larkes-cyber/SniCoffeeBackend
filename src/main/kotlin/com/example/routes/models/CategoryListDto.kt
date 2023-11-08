package com.example.routes.models

import com.example.domain.model.CoffeeCategory
import kotlinx.serialization.Serializable

@Serializable
data class CategoryListDto(
    val categoryList:List<CategoryDto>
)