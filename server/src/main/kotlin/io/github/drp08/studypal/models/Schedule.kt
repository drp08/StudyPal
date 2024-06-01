package io.github.drp08.studypal.models

import io.ktor.resources.Resource

@Resource("/schedule")
data class Schedule(val name: String = "Subject")