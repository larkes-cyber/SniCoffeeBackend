package com.example.domain.usecase.coffee_category

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