package com.example.data.repository

import com.example.data.model.CoffeeEntity
import com.example.data.source.coffee.CoffeeDiskDataSource
import com.example.data.source.coffee.CoffeeImageStorageDataSource
import com.example.data.source.user.UserDiskDataSource
import com.example.domain.repository.CoffeeRepository
import java.io.File

class CoffeeRepositoryImpl(
    private val coffeeDiskDataSource: CoffeeDiskDataSource,
    private val coffeeImageStorageDataSource: CoffeeImageStorageDataSource
):CoffeeRepository {
    override suspend fun insertCoffee(coffeeEntity: CoffeeEntity) {
        coffeeDiskDataSource.insertCoffee(coffeeEntity)
    }

    override suspend fun changeCoffeePhoto(id: String, image: File) {
        coffeeImageStorageDataSource.uploadPhoto(id = id, file = image)
    }

    override suspend fun deleteCoffee(id: String) {
        coffeeDiskDataSource.deleteCoffee(id)
    }

    override suspend fun getCoffeeByCategory(id: String): List<CoffeeEntity> {
        return coffeeDiskDataSource.getCoffeeByCategory(id)
    }

    override suspend fun findCoffeeBySymbols(symbols: String): List<CoffeeEntity> {
        return coffeeDiskDataSource.findCoffeeBySymbols(symbols)
    }
}