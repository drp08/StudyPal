package io.github.drp08.studypal

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import java.time.LocalTime

fun Route.scheduleRouting() {
    route("/schedule") {
        get("name") {
            val name = call.parameters["name"]
            val subject = Subject(
                name = name!!,
                startTime = LocalTime.of(22,0,0).toSecondOfDay(),
                endTime = LocalTime.of(23,0,0).toSecondOfDay(),
                noTotalSessions = 6
            )
            call.respond(subject)
        }
        post {
            val subject = call.receive<Subject>()
            call.respondText("Subject created", status = HttpStatusCode.Created)
        }
    }
}