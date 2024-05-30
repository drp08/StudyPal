package io.github.drp08.studypal.di

import io.github.drp08.studypal.data.SchedulingRepositoryImpl
import io.github.drp08.studypal.domain.SchedulingRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.URLProtocol

object AppModule {
    val httpClient: HttpClient = HttpClient {
        install(Resources)
        defaultRequest {
            host = "0.0.0.0"
            port = 8080
            url {
                protocol = URLProtocol.HTTP
            }
        }
    }

    val schedulingRepository: SchedulingRepository = SchedulingRepositoryImpl(httpClient)
}