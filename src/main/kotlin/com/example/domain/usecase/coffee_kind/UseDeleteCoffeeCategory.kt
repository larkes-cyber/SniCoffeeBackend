package com.example.domain.usecase.coffee_kind

import com.example.domain.mapper.toCoffee
import com.example.domain.model.Coffee
import com.example.domain.repository.CoffeeCategoryRepository
import com.example.utils.Resource

class UseDeleteCoffeeCategory(
    private val coffeeCategoryRepository: CoffeeCategoryRepository
) {

    suspend fun execute(categoryId:String): Resource<String> {
        return try {
            coffeeCategoryRepository.deleteCoffeeCategory(categoryId)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}