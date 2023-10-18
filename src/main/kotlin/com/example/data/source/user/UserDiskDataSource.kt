package com.example.data.source.user

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.model.UserEntity
import com.example.utils.Constants.HMACKEY
import com.example.utils.Constants.ISSUER
import com.example.utils.Constants.JWT_SECRET_KEY
import com.example.utils.Constants.SUBJECT
import io.ktor.util.*
import sun.security.x509.CertificateIssuerExtension

import javax.crypto.Mac
import kotlin.math.log

interface UserDiskDataSource {

    suspend fun registerUser(userEntity: UserEntity):UserEntity
    suspend fun authUser(login:String, password:String):UserEntity?
    suspend fun getUserInfo(id:String):UserEntity?
    suspend fun addFavoriteCoffee(userId:String, coffeeId:String)
    suspend fun deleteFavoriteCoffee(userId:String, coffeeId:String)
    suspend fun editUser(userEntity: UserEntity)
    suspend fun findUserByLogin(login:String):UserEntity?

    companion object{


        private val algorithm = Algorithm.HMAC512(JWT_SECRET_KEY)
        private val verifier = JWT
            .require(algorithm)
            .withIssuer(ISSUER)
            .build()

        fun generateToken(password:String, login:String): String {
            return JWT.create()
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .withClaim("email", login)
                .withClaim("password", password)
                .sign(algorithm)
        }

        fun generatePasswordHash(password: String): String {
            val hmac = Mac.getInstance("HmacSHA1")
            hmac.init(HMACKEY)
            return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
        }


    }

}