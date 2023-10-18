package com.example.domain.repository

import com.example.data.model.CoffeeEntity
import java.io.File

interface CoffeeRepository {

    suspend fun insertCoffee(coffeeEntity: CoffeeEntity)
    suspend fun changeCoffeePhoto(id:String, image:File)
    suspend fun deleteCoffee(id:String)
    suspend fun editCoffee(coffeeEntity: CoffeeEntity)


}