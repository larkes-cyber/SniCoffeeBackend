package com.example.data.source.order

import com.example.data.model.OrderEntity
import com.mongodb.client.model.Filters
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne

class OrderDiskDataSourceImpl(
    private val coroutineDatabase: CoroutineDatabase
):OrderDiskDataSource {

    private val orderDb = coroutineDatabase.getCollection<OrderEntity>()
    override suspend fun insertOrder(orderEntity: OrderEntity) {
        val generatedId = ObjectId().toString()
        orderEntity.id = generatedId
        orderDb.insertOne(orderEntity)
    }

    override suspend fun deleteOrder(id: String) {
        val filter = Filters.eq("_id", id)
        orderDb.deleteOne(filter)
    }

    override suspend fun getOrders(): List<OrderEntity> {
        return orderDb.find().toList()
    }

    override suspend fun findOrderById(id: String): OrderEntity {
        return orderDb.findOne(Filters.eq("_id", id))!!
    }
}