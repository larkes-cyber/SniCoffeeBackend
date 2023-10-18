package com.example.di.domain

import com.example.domain.usecase.user.*
import org.koin.dsl.module

val userDomainModule = module {

    factory<UseAddFavoriteCoffee> {
        UseAddFavoriteCoffee(get())
    }

    factory<UseAuthUser> {
        UseAuthUser(get())
    }

    factory<UseChangeUserPhoto> {
        UseChangeUserPhoto(get())
    }

    factory<UseDeleteFavoriteCoffee> {
        UseDeleteFavoriteCoffee(get())
    }

    factory<UseEditUserInfo> {
        UseEditUserInfo(get())
    }

    factory<UseGetUserInfo> {
        UseGetUserInfo(get())
    }

    factory<UseRegisterUser> {
        UseRegisterUser(get())
    }
    factory<UseCheckUserExists> {
        UseCheckUserExists(get())
    }

    factory<UseCheckCorrectSession> {
        UseCheckCorrectSession(get())
    }

}