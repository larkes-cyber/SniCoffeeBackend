package com.example.utils

import javax.crypto.spec.SecretKeySpec

object Constants {
    const val ISSUER = "SniNotes"
    const val SUBJECT = "sniauth"
    val JWT_SECRET_KEY = "JWT_SECRET"
    val HASH_SECRET_KEY = "JWT_SECRET"
    val HMACKEY = SecretKeySpec(HASH_SECRET_KEY.toByteArray(), "HmacSHA1")
}