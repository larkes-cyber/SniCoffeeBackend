package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import com.example.utils.Resource

class UseAddFavoriteCoffee(
    private val userRepository: UserRepository
) {

    suspend fun execute(userId:String, coffeeId:String): Resource<String> {
        return try {
            userRepository.addFavoriteCoffee(coffeeId = coffeeId, userId = userId)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}