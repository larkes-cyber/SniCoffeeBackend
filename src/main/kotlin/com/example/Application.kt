package com.example

import com.example.di.data.dataSourceModule
import com.example.di.data.repositoryModule
import com.example.di.domain.coffeeCategoryDomainModule
import com.example.di.domain.coffeeDomainModule
import com.example.di.domain.orderDomainModule
import com.example.di.domain.userDomainModule
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.application.install
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin
import org.koin.ktor.plugin.koin
import org.koin.logger.slf4jLogger
import java.io.File

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}
fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(dataSourceModule)
        modules(repositoryModule)
        modules(coffeeCategoryDomainModule)
        modules(coffeeDomainModule)
        modules(orderDomainModule)
        modules(userDomainModule)
    }

    configureRouting()
    configureSerialization()
    configureSecurity()

}
