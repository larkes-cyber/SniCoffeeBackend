package com.example.data.source.coffee_category

import com.example.data.model.CoffeeCategoryEntity
import com.mongodb.client.model.Filters
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne

class CoffeeCategoryDiskDataSourceImpl(
    private val db:CoroutineDatabase
):CoffeeCategoryDiskDataSource {

    private val coffeeCategoryDb = db.getCollection<CoffeeCategoryEntity>()

    override suspend fun insertCoffeeCategory(coffeeCategoryEntity: CoffeeCategoryEntity) {
        val generatedId = ObjectId().toString()
        coffeeCategoryEntity.id = generatedId
        coffeeCategoryDb.insertOne(coffeeCategoryEntity)
    }

    override suspend fun getCoffeeCategories(): List<CoffeeCategoryEntity> {
        return coffeeCategoryDb.find().toList()
    }

    override suspend fun deleteCoffeeCategory(id: String) {
        val filter = Filters.eq("_id", id)
        coffeeCategoryDb.deleteOne(filter)
    }
}