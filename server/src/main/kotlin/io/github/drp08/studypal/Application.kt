package io.github.drp08.studypal

import io.github.drp08.studypal.routes.scheduleRouting
import io.github.drp08.studypal.utils.Constants
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(
        Netty,
        port = Constants.SERVER_PORT,
        host = Constants.SERVER_HOST,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureResources()
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

fun Application.configureResources() {
    install(Resources)
}