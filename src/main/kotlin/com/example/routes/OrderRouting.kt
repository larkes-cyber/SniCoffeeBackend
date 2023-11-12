package com.example.routes

import com.example.domain.mapper.toCoffeeCategory
import com.example.domain.mapper.toOrder
import com.example.domain.mapper.toOrderDto
import com.example.domain.usecase.order.UseAddOrder
import com.example.domain.usecase.order.UseCompleteOrder
import com.example.domain.usecase.order.UseGetOrders
import com.example.domain.usecase.order.UseRemoveOrder
import com.example.domain.usecase.user.UseCheckCorrectSession
import com.example.routes.models.CategoryDto
import com.example.routes.models.OrderDto
import com.example.routes.models.OrderList
import com.example.routes.models.SessionDto
import com.example.routes.navigation.OrderBranch
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.orderRouting() {

    val useAddOrder by inject<UseAddOrder>()
    val useCompleteOrder by inject<UseCompleteOrder>()
    val useGetOrders by inject<UseGetOrders>()
    val useRemoveOrder by inject<UseRemoveOrder>()
    val useCheckCorrectSession by inject<UseCheckCorrectSession>()

    route("order"){
        post(OrderBranch.AddOrderBranch.route) {
            val order = call.receive<OrderDto>()
            if(useCheckCorrectSession.execute(order.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useAddOrder.execute(order.toOrder())
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }
        post(OrderBranch.DeleteOrderBranch.route) {
            val order = call.receive<OrderDto>()
            if(useCheckCorrectSession.execute(order.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useRemoveOrder.execute(order.id!!)
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }
        post(OrderBranch.CompleteOrderBranch.route) {
            val order = call.receive<OrderDto>()
            if(useCheckCorrectSession.execute(order.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            useCompleteOrder.execute(order.id!!)
            call.respondText(Constants.SUCCESS_MESSAGE, status = HttpStatusCode.OK)
        }
        post(OrderBranch.GetOrdersBranch.route) {
            val order = call.receive<SessionDto>()
            if(useCheckCorrectSession.execute(order.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            val orders = useGetOrders.execute().data
            call.respond(OrderList(orders!!.map { it.toOrderDto() }))
        }

        post(OrderBranch.GetUserOrders.route) {
            val order = call.receive<SessionDto>()
            if(useCheckCorrectSession.execute(order.session) == Constants.USER_DOESNT_EXIST){
                call.respondText(Constants.INVALID_SESSION_MESSAGE, status = HttpStatusCode.Unauthorized)
                return@post
            }
            val orders = useGetOrders.execute().data?.filter {
                it.userId == order.session
            } ?: listOf()
            call.respond(OrderList(orders.map { it.toOrderDto() }))

        }


    }

}