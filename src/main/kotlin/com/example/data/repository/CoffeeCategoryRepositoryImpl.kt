package com.example.data.repository

import com.example.data.model.CoffeeCategoryEntity
import com.example.data.source.coffee_category.CoffeeCategoryDiskDataSource
import com.example.domain.model.CoffeeCategory
import com.example.domain.repository.CoffeeCategoryRepository

class CoffeeCategoryRepositoryImpl(
    private val coffeeCategoryDiskDataSource: CoffeeCategoryDiskDataSource
):CoffeeCategoryRepository {
    override suspend fun insertCoffeeCategory(coffeeCategory: CoffeeCategoryEntity) {
        coffeeCategoryDiskDataSource.insertCoffeeCategory(coffeeCategory)
    }

    override suspend fun deleteCoffeeCategory(id: String) {
        coffeeCategoryDiskDataSource.deleteCoffeeCategory(id)
    }

    override suspend fun getCoffeeCategories(): List<CoffeeCategoryEntity> {
        return coffeeCategoryDiskDataSource.getCoffeeCategories()
    }

}