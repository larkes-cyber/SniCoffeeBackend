package com.example.domain.usecase.coffee

import com.example.domain.mapper.toCoffee
import com.example.domain.model.Coffee
import com.example.domain.repository.CoffeeRepository
import com.example.utils.Resource

class UseGetAllCoffee(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute():Resource<List<Coffee>>{
        return try {
            Resource.Success(coffeeRepository.getAllCoffee().map { it.toCoffee() })
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}