package com.example.routes

import com.example.domain.usecase.coffee_category.UseAddCoffeeCategory
import com.example.domain.usecase.coffee_category.UseDeleteCoffeeCategory
import com.example.domain.usecase.coffee_category.UseEditCoffeeCategory
import com.example.domain.usecase.coffee_category.UseGetCoffeeCategories
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.coffeeCategoryRouting() {

    val useAddCoffeeCategory by inject<UseAddCoffeeCategory>()
    val useDeleteCoffeeCategory by inject<UseDeleteCoffeeCategory>()
    val useEditCoffeeCategory by inject<UseEditCoffeeCategory>()
    val useGryCoffeeCategories by inject<UseGetCoffeeCategories>()

    route("coffee_category"){

    }

}