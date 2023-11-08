package com.example.di.domain

import com.example.domain.usecase.coffee.*
import com.example.domain.usecase.user.UseAuthUser
import org.koin.dsl.module

val coffeeDomainModule = module {

    factory<UseAddCoffee> {
        UseAddCoffee(get())
    }

    factory<UseChangeCoffeePhoto> {
        UseChangeCoffeePhoto(get())
    }

    factory<UseDeleteCoffee> {
        UseDeleteCoffee(get())
    }

    factory<UseEditCoffee> {
        UseEditCoffee(get())
    }

    factory<UseFindCoffee> {
        UseFindCoffee(get())
    }

    factory<UseGetCoffeeByCategory> {
        UseGetCoffeeByCategory(get())
    }

    factory<UseGetAllCoffee> {
        UseGetAllCoffee(get())
    }
}