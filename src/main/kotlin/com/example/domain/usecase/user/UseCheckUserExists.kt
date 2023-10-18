package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import com.example.utils.Constants.USER_DOESNT_EXIST
import com.example.utils.Constants.USER_EXISTS
import com.example.utils.Resource

class UseCheckUserExists(
    private val userRepository: UserRepository
) {

    suspend fun execute(login:String = ""):Boolean{
       val user = userRepository.findUserByLogin(login)
        return if(user == null) USER_DOESNT_EXIST else USER_EXISTS
    }

}