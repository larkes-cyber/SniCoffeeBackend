package com.example.domain.usecase.order

import com.example.domain.mapper.toOrderEntity
import com.example.domain.model.Order
import com.example.domain.repository.OrderRepository
import com.example.utils.Resource

class UseCompleteOrder(
    private val orderRepository: OrderRepository
) {

    suspend fun execute(id: String): Resource<String> {
        return try {
            orderRepository.completeOrder(id)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}