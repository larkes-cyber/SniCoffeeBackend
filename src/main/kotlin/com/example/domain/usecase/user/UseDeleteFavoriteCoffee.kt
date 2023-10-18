package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import com.example.utils.Resource

class UseDeleteFavoriteCoffee(
    private val userRepository: UserRepository
) {

    suspend fun execute(userId:String, coffeeId:String): Resource<String> {
        return try {
            userRepository.deleteFavoriteCoffee(coffeeId = coffeeId, userId = userId)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}