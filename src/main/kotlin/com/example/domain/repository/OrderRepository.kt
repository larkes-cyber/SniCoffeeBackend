package com.example.domain.repository

import com.example.data.model.OrderEntity

interface OrderRepository {

    suspend fun insertOrder(orderEntity: OrderEntity)
    suspend fun completeOrder(id:String)
    suspend fun deleteOrder(id:String)
    suspend fun getOrders():List<OrderEntity>

}