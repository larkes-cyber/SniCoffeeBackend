package com.example.domain.usecase.coffee_category

import com.example.domain.mapper.toCoffeeKind
import com.example.domain.model.CoffeeCategory
import com.example.domain.repository.CoffeeCategoryRepository
import com.example.utils.Resource

class UseGetCoffeeCategories(
    private val coffeeCategoryRepository: CoffeeCategoryRepository
) {

    suspend fun execute(): Resource<List<CoffeeCategory>> {
        return try {
           val categories = coffeeCategoryRepository.getCoffeeCategories().map { it.toCoffeeKind() }
            Resource.Success(categories)
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}