package com.example.domain.usecase.order

import com.example.domain.mapper.toCoffeeKindEntity
import com.example.domain.mapper.toOrderEntity
import com.example.domain.model.CoffeeCategory
import com.example.domain.model.Order
import com.example.domain.repository.OrderRepository
import com.example.utils.Resource

class UseAddOrder(
    private val orderRepository: OrderRepository
) {

    suspend fun execute(order:Order): Resource<String> {
        return try {
            orderRepository.insertOrder(order.toOrderEntity())
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}