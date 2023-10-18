package com.example.di.data

import com.example.data.repository.CoffeeCategoryRepositoryImpl
import com.example.data.repository.CoffeeRepositoryImpl
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.CoffeeCategoryRepository
import com.example.domain.repository.CoffeeRepository
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }

    single<CoffeeRepository> {
        CoffeeRepositoryImpl(get(), get())
    }

    single<CoffeeCategoryRepository> {
        CoffeeCategoryRepositoryImpl(get())
    }

    single<OrderRepository> {
        OrderRepositoryImpl(get())
    }

}