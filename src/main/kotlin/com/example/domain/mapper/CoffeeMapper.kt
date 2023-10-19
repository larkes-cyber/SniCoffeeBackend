package com.example.domain.mapper

import com.example.data.model.CoffeeEntity
import com.example.domain.model.Coffee
import com.example.routes.models.CoffeeDto

fun Coffee.toCoffeeEntity():CoffeeEntity{
    return CoffeeEntity(
        id = id,
        photoSrc = photoSrc,
        includeBeans = includeBeans,
        includeMilk = includeMilk,
        categoryId = categoryId,
        categoryTitle = categoryTitle,
        subtitle = subtitle,
        description = description,
        totalScore = totalScore,
        scoreCount = scoreCount,
        price = price
    )
}

fun CoffeeEntity.toCoffee():Coffee{
    return Coffee(
        id = id,
        photoSrc = photoSrc,
        includeBeans = includeBeans,
        includeMilk = includeMilk,
        categoryId = categoryId,
        categoryTitle = categoryTitle,
        subtitle = subtitle,
        description = description,
        totalScore = totalScore,
        scoreCount = scoreCount,
        price = price
    )
}

fun CoffeeDto.toCoffee():Coffee{
    return Coffee(
        id = id,
        photoSrc = photoSrc,
        includeBeans = includeBeans,
        includeMilk = includeMilk,
        categoryId = categoryId,
        categoryTitle = categoryTitle,
        subtitle = subtitle,
        description = description,
        totalScore = totalScore,
        scoreCount = scoreCount,
        price = price
    )
}