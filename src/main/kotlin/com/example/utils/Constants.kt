package com.example.utils

import javax.crypto.spec.SecretKeySpec

object Constants {
    const val USER_EXISTS = true
    const val USER_DOESNT_EXIST = false
    const val ISSUER = "SniNotes"
    const val SUBJECT = "sniauth"
    const val JWT_SECRET_KEY = "JWT_SECRET"
    const val HASH_SECRET_KEY = "JWT_SECRET"
    val HMACKEY = SecretKeySpec(HASH_SECRET_KEY.toByteArray(), "HmacSHA1")

    const val INCORRECT_DATA_MESSAGE = "incorrect data"
    const val SHORT_PASSWORD_MESSAGE = "incorrect password"
    const val INVALID_SESSION_MESSAGE = "invalid session message"
    const val SUCCESS_MESSAGE = "success"
}