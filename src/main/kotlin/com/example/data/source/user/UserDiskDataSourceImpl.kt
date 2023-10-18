package com.example.data.source.user

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.model.UserEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import sun.security.x509.CertificateIssuerExtension.ISSUER
import java.util.logging.Filter

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

        val filter = Filters.eq("_id", token)

        return userDb.findOne(filter)
    }

    override suspend fun getUserInfo(id: String): UserEntity? {
        val filter = Filters.eq("_id", id)
        return userDb.findOne(filter)
    }

    override suspend fun addFavoriteCoffee(userId: String, coffeeId: String) {

        val filter = Filters.eq("_id", userId)
        val user = userDb.findOne(filter)

        if(user != null){
            val favoriteCoffee = (if(user.favoriteCoffee.isNotEmpty()) user.favoriteCoffee.split(";") else listOf()).toMutableList()
            favoriteCoffee.add(coffeeId)
            user.favoriteCoffee = favoriteCoffee.joinToString(";")
            userDb.insertOne(user)
        }
    }

    override suspend fun deleteFavoriteCoffee(userId: String, coffeeId: String) {
        val filter = Filters.eq("_id", userId)
        val user = userDb.findOne(filter)
        if(user != null){
            val favoriteCoffee = (if(user.favoriteCoffee.isNotEmpty()) user.favoriteCoffee.split(";") else listOf()).toMutableList()
            favoriteCoffee.remove(coffeeId)
            user.favoriteCoffee = favoriteCoffee.joinToString(";")
            userDb.insertOne(user)
        }
     }

    override suspend fun editUser(userEntity: UserEntity) {
        val filter = Filters.eq("_id", userEntity.id)
        val user = userDb.findOne(filter)

        if(user != null){
            userDb.insertOne(user)
        }
    }

    override suspend fun findUserByLogin(login: String): UserEntity? {
        val filter = Filters.eq("login", login)
        val user = userDb.findOne(filter)
        return user
    }
}