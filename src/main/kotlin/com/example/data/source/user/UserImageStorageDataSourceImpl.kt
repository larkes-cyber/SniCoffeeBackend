package com.example.data.source.user

import java.io.File

class UserImageStorageDataSourceImpl:UserImageStorageDataSource {
    override suspend fun uploadPhoto(id: String, file: ByteArray): String {
        val name = "$id.jpg"
        val file = File("user_images/$name").writeBytes(file)

        return ""
    }
}