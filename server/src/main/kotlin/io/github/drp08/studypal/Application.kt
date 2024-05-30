package io.github.drp08.studypal

import SERVER_PORT
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
}

fun Application.configureRouting() {
    routing {
        scheduleRouting()
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}