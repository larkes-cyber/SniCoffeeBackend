package com.example.routes

import com.example.domain.mapper.toUser
import com.example.domain.mapper.toUserDto
import com.example.domain.model.User
import com.example.domain.usecase.user.*
import com.example.routes.models.FavoriteCoffeeDto
import com.example.routes.models.LoginDto
import com.example.routes.models.SessionDto
import com.example.routes.models.UserDto
import com.example.routes.navigation.UserBranch
import com.example.utils.Constants
import com.example.utils.Constants.INCORRECT_DATA_MESSAGE
import com.example.utils.Constants.INVALID_SESSION_MESSAGE
import com.example.utils.Constants.SHORT_PASSWORD_MESSAGE
import com.example.utils.Constants.SUCCESS_MESSAGE
import com.example.utils.Constants.USER_DOESNT_EXIST
import com.example.utils.Constants.USER_EXISTS
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.io.File
import kotlin.math.log

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

        get(UserBranch.GetUserImage.route) {
            val filename = call.parameters["file_name"]!!
            val file = File("user_images/$filename.jpg")
            if(file.exists()) {
                call.respondFile(file)
            }
            else call.respond(HttpStatusCode.BadRequest)
        }

        post(UserBranch.RegisterBranch.route){
            val emailPattern = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
            val user = call.receive<UserDto>()

            if(useCheckUserExists.execute(user.login)){
                call.respondText(INCORRECT_DATA_MESSAGE, status =  HttpStatusCode.Unauthorized)
                return@post
            }
            if(!emailPattern.matches(user.login)){
                call.respondText(INCORRECT_DATA_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            if(user.password.length < 8){
                call.respondText(SHORT_PASSWORD_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }

            val res = useRegisterUser.execute(user.toUser())
            call.respond(res.data!!.toUserDto())
        }
        post(UserBranch.AuthBranch.route) {
            val login = call.receive<LoginDto>()
            val user = useAuthUser.execute(login = login.login, password = login.password)
            if(user.data == null){
                call.respondText(INCORRECT_DATA_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            call.respond(user.data.toUserDto() )
        }
        post(UserBranch.EditProfileBranch.route) {
            val user = call.receive<UserDto>()
            if(user.id == null || useCheckCorrectSession.execute(user.id) == USER_DOESNT_EXIST){
                call.respondText(INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useEditUserInfo.execute(user.toUser())
            call.respondText(SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }

        post(UserBranch.AddFavoriteCoffee.route) {
            println("34234234234  sdfsdfsdfgdfgdfgdfgdfgdfgdfg")
            val coffee = call.receive<FavoriteCoffeeDto>()
            println("34234234234  $coffee")

            if(useCheckCorrectSession.execute(coffee.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            println("34234234234  sdfsdfsdfgdfgdfgdfgdfgdfgdfg")
            useAddFavoriteCoffee.execute(userId = coffee.session, coffeeId = coffee.coffeeId)
            println("###############" + useGetUserInfo.execute(coffee.session).toString())
            call.respondText(SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }

        post(UserBranch.RemoveFavoriteCoffee.route) {
            val coffee = call.receive<FavoriteCoffeeDto>()
            if(useCheckCorrectSession.execute(coffee.session)  == Constants.USER_DOESNT_EXIST ){
                call.respondText(INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useDeleteFavoriteCoffee.execute(userId = coffee.session, coffeeId = coffee.coffeeId)
            call.respondText(SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }

        post(UserBranch.GetUserInfo.route) {
            val session = call.receive<SessionDto>()
            if(useCheckCorrectSession.execute(session.session) == USER_DOESNT_EXIST){
                call.respondText(INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            val user = useGetUserInfo.execute(session.session)
            call.respond(user.data!!.toUserDto())
        }
        post(UserBranch.ChangeProfilePhotoBranch.route) {
            val multipartData = call.receiveMultipart()
            val session = call.parameters["session"] ?: return@post call.respondText(INVALID_SESSION_MESSAGE, status = HttpStatusCode.BadRequest)
            val login = call.parameters["login"] ?: return@post call.respondText(INCORRECT_DATA_MESSAGE, status = HttpStatusCode.BadRequest)
            if(useCheckCorrectSession.execute(session) == USER_DOESNT_EXIST){
                call.respondText(INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            multipartData.forEachPart {part ->
                if(part is PartData.FileItem){
                    val fileBytes = part.streamProvider().readBytes()
                    useChangeUserPhoto.execute(userId = login, photo = fileBytes)
                    part.dispose()
                }
            }
            call.respondText(SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }

    }
}
