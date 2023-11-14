package com.example.di.data

import com.example.data.source.coffee.CoffeeDiskDataSource
import com.example.data.source.coffee.CoffeeDiskDataSourceImpl
import com.example.data.source.coffee.CoffeeImageStorageDataSource
import com.example.data.source.coffee.CoffeeImageStorageDataSourceImpl
import com.example.data.source.coffee_category.CoffeeCategoryDiskDataSource
import com.example.data.source.coffee_category.CoffeeCategoryDiskDataSourceImpl
import com.example.data.source.order.OrderDiskDataSource
import com.example.data.source.order.OrderDiskDataSourceImpl
import com.example.data.source.user.UserDiskDataSource
import com.example.data.source.user.UserDiskDataSourceImpl
import com.example.data.source.user.UserImageStorageDataSource
import com.example.data.source.user.UserImageStorageDataSourceImpl
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val dataSourceModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("coffee_db_yt")
    }
    single<CoffeeDiskDataSource> {
        CoffeeDiskDataSourceImpl(get())
    }
    single<CoffeeImageStorageDataSource> {
        CoffeeImageStorageDataSourceImpl()
    }
    single<CoffeeCategoryDiskDataSource> {
        CoffeeCategoryDiskDataSourceImpl(get())
    }
    single<OrderDiskDataSource> {
        OrderDiskDataSourceImpl(get())
    }
    single<UserDiskDataSource> {
        UserDiskDataSourceImpl(get())
    }
    single<UserImageStorageDataSource> {
        UserImageStorageDataSourceImpl()
    }

}