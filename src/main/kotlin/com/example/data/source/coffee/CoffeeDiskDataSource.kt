package com.example.data.source.coffee

import com.example.data.model.CoffeeEntity

interface CoffeeDiskDataSource {

    suspend fun insertCoffee(coffeeEntity: CoffeeEntity)
    suspend fun deleteCoffee(id:String)
    suspend fun getCoffeeByCategory(id:String):List<CoffeeEntity>
    suspend fun findCoffeeBySymbols(symbols:String):List<CoffeeEntity>
    suspend fun getAllCoffee():List<CoffeeEntity>


}