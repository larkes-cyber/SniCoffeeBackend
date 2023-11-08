package com.example.data.source.coffee

import java.io.File

class CoffeeImageStorageDataSourceImpl:CoffeeImageStorageDataSource {
    override suspend fun uploadPhoto(id: String, file: ByteArray):String {
        val name = "$id.jpg"
        File("coffee_images/$name").writeBytes(file)

        return "coffee_images/$name"
    }
}