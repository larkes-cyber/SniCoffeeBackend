package com.example.domain.usecase.coffee

import com.example.domain.mapper.toCoffeeEntity
import com.example.domain.model.Coffee
import com.example.domain.repository.CoffeeRepository
import com.example.utils.Resource

class UseDeleteCoffee(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute(id:String): Resource<String> {
        return try {
            coffeeRepository.deleteCoffee(id)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}