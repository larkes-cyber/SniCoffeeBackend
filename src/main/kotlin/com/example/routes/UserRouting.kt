package com.example.routes

import com.example.domain.mapper.toUser
import com.example.domain.model.User
import com.example.domain.usecase.user.*
import com.example.routes.models.UserDto
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.File

fun Routing.userRouting() {

    val useAddFavoriteCoffee by inject<UseAddFavoriteCoffee>()
    val useAuthUser by inject<UseAuthUser>()
    val useChangeUserPhoto by inject<UseChangeUserPhoto>()
    val useDeleteFavoriteCoffee by inject<UseDeleteFavoriteCoffee>()
    val useEditUserInfo by inject<UseEditUserInfo>()
    val useGetUserInfo by inject<UseGetUserInfo>()
    val useRegisterUser by inject<UseRegisterUser>()
    val useCheckCorrectSession by inject<UseCheckCorrectSession>()
    val useCheckUserExists by inject<UseCheckUserExists>()

    route("/user"){
        post("/register"){
            val user = call.receive<UserDto>()
            val res = useRegisterUser.execute(user.toUser())
            call.respondText(res.data!!.id!!)
        }
        post("/upload_image") {
            val session = call.parameters["session"] ?: return@post call.respondText("", status = HttpStatusCode.BadRequest)
            val login = call.parameters["login"] ?: return@post call.respondText("", status = HttpStatusCode.BadRequest)
            val multipartData = call.receiveMultipart()

            multipartData.forEachPart {part ->
                when(part){
                    is PartData.FormItem -> {}
                    is PartData.FileItem -> {
                        val fileBytes = part.streamProvider().readBytes()
                        val file = File("$login.jpg").writeBytes(fileBytes)
                    }
                    else -> {}
                }
                part.dispose()
            }
        }
    }
}
