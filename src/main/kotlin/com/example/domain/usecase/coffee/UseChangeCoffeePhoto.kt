package com.example.domain.usecase.coffee

import com.example.domain.mapper.toCoffeeEntity
import com.example.domain.model.Coffee
import com.example.domain.repository.CoffeeRepository
import com.example.utils.Resource
import java.io.File

class UseChangeCoffeePhoto(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute(id:String, file:File): Resource<String> {
        return try {
            coffeeRepository.changeCoffeePhoto(id, file)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}