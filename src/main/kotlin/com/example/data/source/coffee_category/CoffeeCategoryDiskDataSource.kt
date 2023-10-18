package com.example.data.source.coffee_category

import com.example.data.model.CoffeeCategoryEntity

interface CoffeeCategoryDiskDataSource {

    suspend fun insertCoffeeCategory(coffeeCategoryEntity: CoffeeCategoryEntity)
    suspend fun getCoffeeCategories():List<CoffeeCategoryEntity>
    suspend fun deleteCoffeeCategory(id:String)

}