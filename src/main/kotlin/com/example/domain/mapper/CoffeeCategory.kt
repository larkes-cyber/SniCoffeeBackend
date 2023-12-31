package com.example.domain.mapper

import com.example.data.model.CoffeeCategoryEntity
import com.example.domain.model.CoffeeCategory
import com.example.routes.models.CategoryDto

fun CoffeeCategory.toCoffeeKindEntity():CoffeeCategoryEntity{
    return CoffeeCategoryEntity(
        id = id,
        title = title
    )
}

fun CoffeeCategoryEntity.toCoffeeKind():CoffeeCategory{
    return CoffeeCategory(
        id = id,
        title = title
    )
}

fun CategoryDto.toCoffeeCategory():CoffeeCategory{
    return CoffeeCategory(
        id = id,
        title = title
    )
}

fun CoffeeCategory.toCategoryDto():CategoryDto{
    return CategoryDto(
        id = id,
        title = title,
        session = ""
    )
}