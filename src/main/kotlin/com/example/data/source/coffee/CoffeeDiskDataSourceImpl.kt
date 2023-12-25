package com.example.data.source.coffee

import com.example.data.model.CoffeeCategoryEntity
import com.example.data.model.CoffeeEntity
import com.example.data.model.OrderEntity
import com.example.data.model.UserEntity
import com.mongodb.client.model.Filters
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne

class CoffeeDiskDataSourceImpl(
    private val coroutineDatabase: CoroutineDatabase
):CoffeeDiskDataSource {

    val coffeeDb = coroutineDatabase.getCollection<CoffeeEntity>()
    override suspend fun insertCoffee(coffeeEntity: CoffeeEntity) {
        val generatedId = ObjectId().toString()
        coffeeEntity.id = generatedId
        coffeeDb.insertOne(coffeeEntity)
    }

    override suspend fun deleteCoffee(id: String) {
        val filter = Filters.eq("id", id)
        coffeeDb.deleteOne(filter)
    }

    override suspend fun getCoffeeByCategory(id: String): List<CoffeeEntity> {
        val coffee = coffeeDb.find().toList()
        val filter = Filters.eq("categoryId", id)
        return coffeeDb.find(filter).toList()
    }

    override suspend fun findCoffeeBySymbols(symbols: String): List<CoffeeEntity> {
        val coffee = coffeeDb.find().toList()
        return coffee.filter {
            it.categoryTitle.contains(symbols, ignoreCase = true) ||
            it.description.contains(symbols, ignoreCase = true) ||
            it.subtitle.contains(symbols, ignoreCase = true)
        }
    }

    override suspend fun getAllCoffee(): List<CoffeeEntity> {
        return coffeeDb.find().toList()
    }
}