package com.example.plugins

import com.example.domain.usecase.coffee.UseAddCoffee
import com.example.routes.coffeeCategoryRouting
import com.example.routes.coffeeRouting
import com.example.routes.orderRouting
import com.example.routes.userRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    install(Routing){
        coffeeRouting()
        coffeeCategoryRouting()
        orderRouting()
        userRouting()
    }
}
