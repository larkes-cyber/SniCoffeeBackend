package com.example.data.source.user


import com.example.data.model.UserEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase


class UserDiskDataSourceImpl(
    private val coroutineDatabase: CoroutineDatabase
):UserDiskDataSource {

    val userDb = coroutineDatabase.getCollection<UserEntity>()



    override suspend fun registerUser(userEntity: UserEntity): UserEntity {
        val passHash = UserDiskDataSource.generatePasswordHash(userEntity.password)
        val token = UserDiskDataSource.generateToken(password = passHash, login = userEntity.login)

        userEntity.id = token
        userEntity.password = passHash

        userDb.insertOne(userEntity)
        return userEntity
    }

    override suspend fun authUser(login: String, password: String): UserEntity? {
        val passHash = UserDiskDataSource.generatePasswordHash(password)
        val token = UserDiskDataSource.generateToken(password = passHash, login = login)
        println("werfgfdedf $login  $password")
        val filter = Filters.eq("id", token)
        val users = userDb.findOne(filter)
        println("erghgfdw###################################erfg ${users!!.name}")
        return userDb.findOne(filter)
    }

    override suspend fun getUserInfo(id: String): UserEntity? {
        println("wefgh################gfef $id")

        val users = userDb.find()
        println("werfgfdfgdfeeeeeeee ${users.toList()[0].id} ${users.toList()[0].login}")

        val filter = Filters.eq("id", id)
        val us = userDb.findOne(filter)
        println("werfgfdfgdfeeeeeeee ${us}")
        return userDb.findOne(filter)
    }

    override suspend fun addFavoriteCoffee(userId: String, coffeeId: String) {

        val filter = Filters.eq("id", userId)
        val user = userDb.findOne(filter)


        println("34234234234  $user")

        if(user != null){
            userDb.deleteOne(filter)
            val favoriteCoffee = (if(user.favoriteCoffee.isNotEmpty()) user.favoriteCoffee.split(";") else listOf()).toMutableList()
            favoriteCoffee.add(coffeeId)
            user.favoriteCoffee = favoriteCoffee.joinToString(";")

            userDb.insertOne(user)
        }
    }

    override suspend fun deleteFavoriteCoffee(userId: String, coffeeId: String) {
        val filter = Filters.eq("id", userId)
        val user = userDb.findOne(filter)
        if(user != null){
            userDb.deleteOne(filter)
            val favoriteCoffee = (if(user.favoriteCoffee.isNotEmpty()) user.favoriteCoffee.split(";") else listOf()).toMutableList()
            favoriteCoffee.remove(coffeeId)
            user.favoriteCoffee = favoriteCoffee.joinToString(";")
            userDb.insertOne(user)
        }
     }

    override suspend fun editUser(userEntity: UserEntity) {
        val filter = Filters.eq("id", userEntity.id)
        val user = userDb.findOne(filter)

        if(user != null){
            userEntity.password = user.password
            userDb.deleteOne(filter)
            userDb.insertOne(userEntity)
        }
    }

    override suspend fun findUserByLogin(login: String): UserEntity? {
        val filter = Filters.eq("login", login)
        val user = userDb.findOne(filter)
        return user
    }
}