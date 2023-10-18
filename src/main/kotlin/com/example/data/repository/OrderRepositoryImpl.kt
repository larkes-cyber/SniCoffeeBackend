package com.example.data.repository

import com.example.data.model.OrderEntity
import com.example.data.source.order.OrderDiskDataSource
import com.example.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val orderDiskDataSource: OrderDiskDataSource
):OrderRepository {
    override suspend fun insertOrder(orderEntity: OrderEntity) {
        orderDiskDataSource.insertOrder(orderEntity)
    }

    override suspend fun completeOrder(id: String) {
        val order = orderDiskDataSource.findOrderById(id)
        order.status = "complete"
        insertOrder(order)
    }

    override suspend fun deleteOrder(id: String) {
        orderDiskDataSource.deleteOrder(id)
    }

    override suspend fun getOrders(): List<OrderEntity> {
        return orderDiskDataSource.getOrders()
    }
}