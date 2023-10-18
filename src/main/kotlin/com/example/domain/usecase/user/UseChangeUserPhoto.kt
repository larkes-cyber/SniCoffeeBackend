package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import com.example.utils.Resource
import java.io.File

class UseChangeUserPhoto(
    private val userRepository: UserRepository
) {

    suspend fun execute(userId:String, photo:File): Resource<String> {
        return try {
            userRepository.changeUserPhoto(id = userId, photo = photo)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}