package com.example.domain.mapper

import com.example.data.model.OrderEntity
import com.example.domain.model.Order

fun Order.toOrderEntity():OrderEntity{

    val strProductsList = productIds.joinToString(";")

    return OrderEntity(
        id = id,
        status = status,
        userId = userId,
        orderPrice = orderPrice,
        productIds = strProductsList
    )
}

fun OrderEntity.toOrder():Order{

    val productsList = productIds.split(";")

    return Order(
        id = id,
        status = status,
        userId = userId,
        orderPrice = orderPrice,
        productIds = productsList
    )
}