package io.github.drp08.studypal.di

import io.github.drp08.studypal.data.SchedulingRepositoryImpl
import io.github.drp08.studypal.domain.SchedulingRepository
import io.github.drp08.studypal.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json

object AppModule {
    val httpClient: HttpClient = HttpClient {
        install(Resources)
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            host = Constants.SERVER_HOST
            port = Constants.SERVER_PORT
            url {
                protocol = URLProtocol.HTTP
            }
        }
    }

    val schedulingRepository: SchedulingRepository = SchedulingRepositoryImpl(httpClient)
}