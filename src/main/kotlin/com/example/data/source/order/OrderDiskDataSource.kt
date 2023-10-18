package com.example.data.source.order

import com.example.data.model.OrderEntity

interface OrderDiskDataSource {
    suspend fun insertOrder(orderEntity: OrderEntity)
    suspend fun deleteOrder(id:String)
    suspend fun getOrders():List<OrderEntity>
    suspend fun findOrderById(id:String):OrderEntity

}