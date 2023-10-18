package com.example.routes

import com.example.domain.usecase.coffee.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.coffeeRouting(){

    val useAddCoffee by inject<UseAddCoffee>()
    val useChangeCoffeePhoto by inject<UseChangeCoffeePhoto>()
    val useDeleteCoffee by inject<UseDeleteCoffee>()
    val useEditCoffee by inject<UseEditCoffee>()
    val useFindCoffee by inject<UseFindCoffee>()
    val useGetCoffeeByCategory by inject<UseGetCoffeeByCategory>()



    route("coffee"){

        get("/coffee"){

        }

    }

}