package com.example.domain.mapper

import com.example.data.model.CoffeeKindEntity
import com.example.domain.model.CoffeeKind

fun CoffeeKind.toCoffeeKindEntity():CoffeeKindEntity{
    return CoffeeKindEntity(
        id = id,
        title = title
    )
}

fun CoffeeKindEntity.toCoffeeKind():CoffeeKind{
    return CoffeeKind(
        id = id,
        title = title
    )
}