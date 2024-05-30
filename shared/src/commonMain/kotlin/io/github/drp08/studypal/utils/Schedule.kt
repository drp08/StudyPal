package io.github.drp08.studypal.utils

import io.ktor.resources.Resource

@Resource("/schedule")
class Schedule(val name: String)