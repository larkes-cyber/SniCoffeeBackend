package com.example.data.source.coffee

import java.io.File

interface CoffeeImageStorageDataSource {
    suspend fun uploadPhoto(id:String, file:File):String
}