package com.example.domain.usecase.coffee_category

import com.example.domain.mapper.toCoffeeKindEntity
import com.example.domain.model.CoffeeCategory
import com.example.domain.repository.CoffeeCategoryRepository
import com.example.utils.Resource

class UseEditCoffeeCategory(
    private val coffeeCategoryRepository: CoffeeCategoryRepository
) {

    suspend fun execute(coffeeCategory: CoffeeCategory): Resource<String> {
        return try {
            if(coffeeCategory.id != null)coffeeCategoryRepository.deleteCoffeeCategory(coffeeCategory.id)
            coffeeCategoryRepository.insertCoffeeCategory(coffeeCategory.toCoffeeKindEntity())
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}