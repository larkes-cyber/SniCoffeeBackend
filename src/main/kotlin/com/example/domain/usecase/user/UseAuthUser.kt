package com.example.domain.usecase.user

import com.example.domain.mapper.toUser
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.example.utils.Resource

class UseAuthUser(
    private val userRepository: UserRepository
) {

    suspend fun execute(login:String, password:String): Resource<User?> {
        return try {
            val user = userRepository.authUser(login = login, password = password)
            println(user)
            Resource.Success(user?.toUser())
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}