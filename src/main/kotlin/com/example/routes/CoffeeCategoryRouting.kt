package com.example.routes

import com.example.domain.mapper.toCoffee
import com.example.domain.mapper.toCoffeeCategory
import com.example.domain.usecase.coffee_category.UseAddCoffeeCategory
import com.example.domain.usecase.coffee_category.UseDeleteCoffeeCategory
import com.example.domain.usecase.coffee_category.UseEditCoffeeCategory
import com.example.domain.usecase.coffee_category.UseGetCoffeeCategories
import com.example.domain.usecase.user.UseCheckCorrectSession
import com.example.routes.models.CategoryDto
import com.example.routes.models.CategoryListDto
import com.example.routes.models.CoffeeDto
import com.example.routes.models.SessionDto
import com.example.routes.navigation.CoffeeCategoryBranch
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.coffeeCategoryRouting() {

    val useAddCoffeeCategory by inject<UseAddCoffeeCategory>()
    val useDeleteCoffeeCategory by inject<UseDeleteCoffeeCategory>()
    val useEditCoffeeCategory by inject<UseEditCoffeeCategory>()
    val useGetCoffeeCategories by inject<UseGetCoffeeCategories>()
    val useCheckCorrectSession by inject<UseCheckCorrectSession>()

    route("coffee_category"){
        post(CoffeeCategoryBranch.AddCoffeeCategory.route) {
            val coffee = call.receive<CategoryDto>()
            if(useCheckCorrectSession.execute(coffee.session)){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useAddCoffeeCategory.execute(coffee.toCoffeeCategory())
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }
        post(CoffeeCategoryBranch.DeleteCoffeeCategory.route) {
            val coffee = call.receive<CategoryDto>()
            if(useCheckCorrectSession.execute(coffee.session)){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useDeleteCoffeeCategory.execute(coffee.id!!)
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }
        post(CoffeeCategoryBranch.EditCoffeeCategory.route) {
            val coffee = call.receive<CategoryDto>()
            if(useCheckCorrectSession.execute(coffee.session)){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useEditCoffeeCategory.execute(coffee.toCoffeeCategory())
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }
        post(CoffeeCategoryBranch.GetCoffeeCategories.route) {
            val session = call.receive<SessionDto>()
            if(useCheckCorrectSession.execute(session.session)){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            val categories = useGetCoffeeCategories.execute()
            call.respond(CategoryListDto(categories.data!!))

        }

    }

}