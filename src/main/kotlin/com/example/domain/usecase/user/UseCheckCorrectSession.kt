package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import com.example.utils.Constants

class UseCheckCorrectSession(
    private val userRepository: UserRepository
) {

    suspend fun execute(id:String):Boolean{
        val user = userRepository.getUserInfo(id)
        return if(user == null) Constants.USER_DOESNT_EXIST else Constants.USER_EXISTS
    }

}