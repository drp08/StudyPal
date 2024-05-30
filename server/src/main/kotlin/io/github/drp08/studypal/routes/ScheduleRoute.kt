package io.github.drp08.studypal.routes

import io.github.drp08.studypal.domain.models.Subject
import io.github.drp08.studypal.models.Database
import io.github.drp08.studypal.models.Schedule
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveText
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import java.time.LocalTime

fun Route.scheduleRouting() {
    get<Schedule> {
        val subject = Subject(
            name = it.name,
            startTime = Database.startTime,
            endTime = Database.startTime + 50 * 60,
            noTotalSessions = 6
        )

        Database.startTime += 60 * 60
        call.respond(subject)
    }
}