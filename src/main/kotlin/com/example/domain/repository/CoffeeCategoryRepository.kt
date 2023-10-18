package com.example.domain.repository

import com.example.data.model.CoffeeCategoryEntity
import com.example.domain.model.CoffeeCategory

interface CoffeeCategoryRepository {
    suspend fun insertCoffeeCategory(coffeeCategory: CoffeeCategoryEntity)
    suspend fun deleteCoffeeCategory(id:String)
    suspend fun getCoffeeCategories():List<CoffeeCategoryEntity>

}