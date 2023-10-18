package com.example.domain.usecase.coffee

import com.example.domain.mapper.toCoffee
import com.example.domain.mapper.toCoffeeEntity
import com.example.domain.model.Coffee
import com.example.domain.model.CoffeeCategory
import com.example.domain.repository.CoffeeRepository
import com.example.utils.Resource

class UseGetCoffeeByCategory(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute(categoryId:String): Resource<List<Coffee>> {
        return try {
            val coffee = coffeeRepository.getCoffeeByCategory(categoryId).map { it.toCoffee() }
            Resource.Success(coffee)
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}