package io.github.drp08.studypal.data

import io.github.drp08.studypal.domain.SchedulingRepository
import io.github.drp08.studypal.domain.models.Subject
import io.github.drp08.studypal.utils.Schedule
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.client.statement.bodyAsText

class SchedulingRepositoryImpl(
    private val client: HttpClient
) : SchedulingRepository {
    override suspend fun getScheduleForSubject(subject: String): Subject {
        val response = client.get(Schedule(name = subject))
        return response.body<Subject>()
    }
}