package com.example.routes

import com.example.domain.mapper.toCoffee
import com.example.domain.mapper.toCoffeeDto
import com.example.domain.usecase.coffee.*
import com.example.domain.usecase.user.UseCheckCorrectSession
import com.example.routes.models.CoffeeDto
import com.example.routes.models.CoffeeListDto
import com.example.routes.models.SearchForCoffeeDto
import com.example.routes.models.SelectCategoryDto
import com.example.routes.navigation.CoffeeBranch
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.File

fun Routing.coffeeRouting(){

    val useAddCoffee by inject<UseAddCoffee>()
    val useChangeCoffeePhoto by inject<UseChangeCoffeePhoto>()
    val useDeleteCoffee by inject<UseDeleteCoffee>()
    val useEditCoffee by inject<UseEditCoffee>()
    val useFindCoffee by inject<UseFindCoffee>()
    val useGetCoffeeByCategory by inject<UseGetCoffeeByCategory>()
    val useCheckCorrectSession by inject<UseCheckCorrectSession>()
    val useGetAllCoffee by inject<UseGetAllCoffee>()


    route("/coffee"){

        get(CoffeeBranch.GetCoffeeImage.route) {
            val filename = call.parameters["file_name"]!!
            val file = File("coffee_images/$filename.png")
            if(file.exists()) {
                call.respondFile(file)
            }
            else call.respond(HttpStatusCode.BadRequest)
        }

        post(CoffeeBranch.AddCoffeeBranch.route) {
            val coffee = call.receive<CoffeeDto>()
            if(useCheckCorrectSession.execute(coffee.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useAddCoffee.execute(coffee.toCoffee())
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }

        post(CoffeeBranch.DeleteCoffeeBranch.route) {
            val coffee = call.receive<CoffeeDto>()
            if(useCheckCorrectSession.execute(coffee.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useDeleteCoffee.execute(coffee.id!!)
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }

        post(CoffeeBranch.EditCoffeeBranch.route) {
            val coffee = call.receive<CoffeeDto>()
            if(useCheckCorrectSession.execute(coffee.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useEditCoffee.execute(coffee.toCoffee())
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }

        post(CoffeeBranch.UploadCoffeePhotoBranch.route) {
            val multipartData = call.receiveMultipart()
            val session = call.parameters["session"] ?: return@post call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.BadRequest)
            val coffee_id = call.parameters["coffee_id"] ?: return@post call.respondText(Constants.INCORRECT_DATA_MESSAGE, status = HttpStatusCode.BadRequest)
            if(useCheckCorrectSession.execute(session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            multipartData.forEachPart {part ->
                if(part is PartData.FileItem){
                    val fileBytes = part.streamProvider().readBytes()
                    useChangeCoffeePhoto.execute(coffee_id, fileBytes)
                    part.dispose()
                }
            }
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }

        post(CoffeeBranch.GetAllCoffee.route) {
            val session = call.parameters["session"] ?: return@post call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.BadRequest)
            if(useCheckCorrectSession.execute(session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            val coffee = useGetAllCoffee.execute().data
            call.respond(coffee!!.map { it.toCoffeeDto() })
        }

    }

}