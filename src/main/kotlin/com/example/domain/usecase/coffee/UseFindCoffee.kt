package com.example.domain.usecase.coffee

import com.example.domain.mapper.toCoffee
import com.example.domain.mapper.toCoffeeEntity
import com.example.domain.model.Coffee
import com.example.domain.repository.CoffeeRepository
import com.example.utils.Resource

class UseFindCoffee(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute(symbols:String): Resource<List<Coffee>> {
        return try {
            val coffee = coffeeRepository.findCoffeeBySymbols(symbols).map {
                it.toCoffee()
            }
            Resource.Success(coffee)
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}