package com.example.routes

import com.example.domain.model.Coffee
import com.example.domain.model.CoffeeCategory
import com.example.domain.usecase.coffee.UseAddCoffee
import com.example.domain.usecase.coffee.UseChangeCoffeePhoto
import com.example.domain.usecase.coffee.UseGetAllCoffee
import com.example.domain.usecase.coffee_category.UseAddCoffeeCategory
import com.example.domain.usecase.coffee_category.UseGetCoffeeCategories
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.File


fun Routing.injectRouting(){

    val useAddCoffee by inject<UseAddCoffee>()
    val useChangeCoffeePhoto by inject<UseChangeCoffeePhoto>()
    val useAddCoffeeCategory by inject<UseAddCoffeeCategory>()
    val useGetCoffeeCategory by inject<UseGetCoffeeCategories>()
    val useGetAllCoffee by inject<UseGetAllCoffee>()

    route("/inject"){

        get("/start") {

            val categories = listOf("Cappuccino", "Machiato", "Latte", "Americano")

            val coffeeSubtitle = listOf("with Chocolate", "with Oat Milk", "with nuts")

            categories.forEach {
                useAddCoffeeCategory.execute(
                    CoffeeCategory(
                        title = it
                    )
                )
            }

            useGetCoffeeCategory.execute().data!!.forEach {category ->
                (0..8).forEach {
                    useAddCoffee.execute(Coffee(
                        includeBeans = listOf(true, false).random(),
                        price = listOf(4.53, 3.90, 3.42, 2.56).random(),
                        includeMilk = listOf(true, false).random(),
                        categoryId = category.id!!,
                        categoryTitle = category.title,
                        subtitle = coffeeSubtitle.random(),
                        description = "A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85ml of fresh milk the beverage, with 25 ml of espresso coffee and 85ml",
                        totalScore = listOf(3.3,4.5,2.3,4.2, 1.2).random(),
                        scoreCount = (1..100).random()
                    ))
                }
            }

            useGetAllCoffee.execute().data!!.forEach {
                useChangeCoffeePhoto.execute(
                    id = it.id!!,
                    file = (listOf(File("coffee_test_images/coffee_1.png"), File("coffee_test_images/coffee_2.png")).random()).readBytes()
                )
            }

        }

    }
}

