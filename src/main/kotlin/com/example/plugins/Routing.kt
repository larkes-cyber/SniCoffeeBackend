package com.example.plugins

import com.example.domain.usecase.coffee.UseAddCoffee
import com.example.routes.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.File

fun Application.configureRouting() {
    install(Routing){
        coffeeRouting()
        coffeeCategoryRouting()
        orderRouting()
        userRouting()
    }
}
