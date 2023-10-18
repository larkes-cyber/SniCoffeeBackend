package com.example.data.source.user

import java.io.File

class UserImageStorageDataSourceImpl:UserImageStorageDataSource {
    override suspend fun uploadPhoto(id: String, file: File): String {
        return ""
    }
}