package com.example.domain.usecase.user

import com.example.data.model.UserEntity
import com.example.domain.mapper.toUser
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.example.utils.Resource

class UseGetUserInfo(
    private val userRepository: UserRepository
) {
    suspend fun execute(userId:String): Resource<User?> {
        return try {
            val user = userRepository.getUserInfo(userId)?.toUser()
            Resource.Success(user)
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }
}