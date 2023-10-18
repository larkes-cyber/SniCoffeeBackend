package com.example.di.domain

import com.example.domain.usecase.order.UseAddOrder
import com.example.domain.usecase.order.UseCompleteOrder
import com.example.domain.usecase.order.UseGetOrders
import com.example.domain.usecase.order.UseRemoveOrder
import org.koin.dsl.module

val orderDomainModule = module {

    factory<UseAddOrder> {
        UseAddOrder(get())
    }

    factory<UseCompleteOrder> {
        UseCompleteOrder(get())
    }

    factory<UseGetOrders> {
        UseGetOrders(get())
    }

    factory<UseRemoveOrder> {
        UseRemoveOrder(get())
    }

}