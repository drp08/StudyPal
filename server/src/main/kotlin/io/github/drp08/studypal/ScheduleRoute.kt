package io.github.drp08.studypal

import io.github.drp08.studypal.domain.models.Subject
import io.ktor.server.application.call
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import java.time.LocalTime

fun Route.scheduleRouting() {
    get("/") {
        call.respondText("Hello world!")
    }
    post("/schedule") {
        val subjectName = call.receiveText()
        val subject = Subject(
            name = subjectName,
            startTime = LocalTime.of(22, 0, 0).toSecondOfDay(),
            endTime = LocalTime.of(23, 0, 0).toSecondOfDay(),
            noTotalSessions = 6
        )

        call.respond(subject)
    }
}