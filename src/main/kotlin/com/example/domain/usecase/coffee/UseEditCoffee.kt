package com.example.domain.usecase.coffee

import com.example.domain.mapper.toCoffeeEntity
import com.example.domain.model.Coffee
import com.example.domain.repository.CoffeeRepository
import com.example.utils.Resource

class UseEditCoffee(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute(coffee: Coffee): Resource<String> {
        return try {
            coffeeRepository.insertCoffee(coffee.toCoffeeEntity())
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}