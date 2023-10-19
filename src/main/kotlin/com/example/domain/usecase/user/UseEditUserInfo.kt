package com.example.domain.usecase.user

import com.example.data.model.UserEntity
import com.example.domain.mapper.toUserEntity
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import com.example.utils.Resource

class UseEditUserInfo(
    private val userRepository: UserRepository
) {

    suspend fun execute(userEntity: User): Resource<String> {
        return try {
            userRepository.editUser(userEntity.toUserEntity())
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}