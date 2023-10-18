package com.example.data.repository

import com.example.data.model.UserEntity
import com.example.data.source.user.UserDiskDataSource
import com.example.data.source.user.UserImageStorageDataSource
import com.example.domain.repository.UserRepository
import java.io.File

class UserRepositoryImpl(
    private val userDiskDataSource: UserDiskDataSource,
    private val userImageStorageDataSource: UserImageStorageDataSource
):UserRepository {
    override suspend fun registerUser(userEntity: UserEntity): UserEntity {
        return userDiskDataSource.registerUser(userEntity)
    }

    override suspend fun authUser(login: String, password: String): UserEntity? {
       return userDiskDataSource.authUser(login = login, password = password)
    }

    override suspend fun changeUserPhoto(id: String, photo: File) {
        userImageStorageDataSource.uploadPhoto(id = id, file = photo)
    }

    override suspend fun getUserInfo(id: String): UserEntity? {
        return userDiskDataSource.getUserInfo(id)
    }

    override suspend fun addFavoriteCoffee(userId: String, coffeeId: String) {
        userDiskDataSource.addFavoriteCoffee(userId = userId, coffeeId = coffeeId)
    }

    override suspend fun deleteFavoriteCoffee(userId: String, coffeeId: String) {
        userDiskDataSource.deleteFavoriteCoffee(userId = userId, coffeeId = coffeeId)
    }
}