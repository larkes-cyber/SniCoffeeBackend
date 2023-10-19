package com.example.data.source.user

import java.io.File

interface UserImageStorageDataSource {
    suspend fun uploadPhoto(id:String, file: ByteArray):String
}