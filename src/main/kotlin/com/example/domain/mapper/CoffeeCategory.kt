package com.example.domain.mapper

import com.example.data.model.CoffeeCategoryEntity
import com.example.domain.model.CoffeeCategory

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