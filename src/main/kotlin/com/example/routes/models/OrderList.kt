package com.example.routes.models

import com.example.domain.model.Order
import kotlinx.serialization.Serializable

@Serializable
data class OrderList(
    val orderList:List<OrderDto>
)