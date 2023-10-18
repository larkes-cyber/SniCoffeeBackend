package com.example.domain.usecase.user

import com.example.data.model.UserEntity
import com.example.domain.mapper.toUser
import com.example.domain.mapper.toUserEntity
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.example.utils.Resource

class UseRegisterUser(
    private val userRepository: UserRepository
) {

    suspend fun execute(userEntity: User): Resource<User> {
        return try {
            val user = userRepository.registerUser(userEntity.toUserEntity()).toUser()
            Resource.Success(user)
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}