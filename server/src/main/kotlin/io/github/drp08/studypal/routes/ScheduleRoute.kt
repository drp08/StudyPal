package io.github.drp08.studypal.routes

import io.github.drp08.studypal.domain.models.Subject
import io.github.drp08.studypal.models.Database
import io.github.drp08.studypal.utils.Schedule
import io.ktor.server.application.call
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.scheduleRouting() {
    get<Schedule> {
        val oneHour = 60 * 60

        val subject = Subject(
            name = it.name,
            startTime = Database.startTime,
            endTime = Database.startTime + oneHour,
            noTotalSessions = 6
        )

        Database.startTime += oneHour
        call.respond(subject)
    }
}