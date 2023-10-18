package com.example.domain.usecase.order

import com.example.domain.mapper.toOrder
import com.example.domain.mapper.toOrderEntity
import com.example.domain.model.Order
import com.example.domain.repository.OrderRepository
import com.example.utils.Resource

class UseGetOrders(
    private val orderRepository: OrderRepository
) {

    suspend fun execute(): Resource<List<Order>> {
        return try {
            val orders = orderRepository.getOrders().map { it.toOrder() }
            Resource.Success(orders)
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}