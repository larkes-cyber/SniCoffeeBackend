package com.example.routes

import com.example.domain.usecase.order.UseAddOrder
import com.example.domain.usecase.order.UseCompleteOrder
import com.example.domain.usecase.order.UseGetOrders
import com.example.domain.usecase.order.UseRemoveOrder
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.orderRouting() {

    val useAddOrder by inject<UseAddOrder>()
    val useCompleteOrder by inject<UseCompleteOrder>()
    val useGetOrders by inject<UseGetOrders>()
    val useRemoveOrder by inject<UseRemoveOrder>()

    route("order"){

    }

}