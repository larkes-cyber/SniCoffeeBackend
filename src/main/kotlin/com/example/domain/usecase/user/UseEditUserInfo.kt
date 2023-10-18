package com.example.domain.usecase.user

import com.example.data.model.UserEntity
import com.example.domain.repository.UserRepository
import com.example.utils.Resource

class UseEditUserInfo(
    private val userRepository: UserRepository
) {

    suspend fun execute(userEntity: UserEntity): Resource<String> {
        return try {
            userRepository.editUser(userEntity)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}