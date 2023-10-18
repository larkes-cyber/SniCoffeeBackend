package com.example.domain.repository

import com.example.data.model.UserEntity
import java.io.File

interface UserRepository {


    suspend fun registerUser(userEntity: UserEntity):UserEntity
    suspend fun authUser(login:String, password:String):UserEntity?
    suspend fun changeUserPhoto(id:String, photo:File)
    suspend fun getUserInfo(id:String):UserEntity?
    suspend fun addFavoriteCoffee(userId:String, coffeeId:String)
    suspend fun deleteFavoriteCoffee(userId:String, coffeeId:String)
    suspend fun editUser(userEntity: UserEntity)
}