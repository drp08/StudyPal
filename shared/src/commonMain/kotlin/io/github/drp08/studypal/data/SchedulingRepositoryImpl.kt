package io.github.drp08.studypal.data

import io.github.drp08.studypal.domain.SchedulingRepository
import io.github.drp08.studypal.domain.models.Subject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class SchedulingRepositoryImpl(
    private val client: HttpClient
) : SchedulingRepository {
    override suspend fun getScheduleForSubject(subject: String): Subject {
        val response = client.post("http://146.169.175.215:8080/schedule") {
            setBody("Chemistry")
        }
        return response.body<Subject>()
    }
}