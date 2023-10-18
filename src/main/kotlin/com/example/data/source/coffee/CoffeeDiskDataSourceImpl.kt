package com.example.data.source.coffee

import com.example.data.model.CoffeeEntity
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
        coffeeDb.insertOne(generatedId)
    }

    override suspend fun deleteCoffee(id: String) {
        val filter = Filters.eq("_id", id)
        coffeeDb.deleteOne(filter)
    }

    override suspend fun getCoffeeByCategory(id: String): List<CoffeeEntity> {
        val filter = Filters.eq("categoryId", id)
        return coffeeDb.find(filter).toList()
    }

    override suspend fun findCoffeeBySymbols(symbols: String): List<CoffeeEntity> {
        val coffee = coffeeDb.find().toList()
        return coffee.filter { it.categoryTitle.contains(symbols, ignoreCase = true) }
    }
}