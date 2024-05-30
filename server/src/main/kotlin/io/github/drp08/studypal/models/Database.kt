package io.github.drp08.studypal.models

import java.time.LocalTime

object Database {
    var startTime = toNearestHour()

    private fun toNearestHour(): Int {
        val now = LocalTime.now()
        return LocalTime.of(now.hour, 0).toSecondOfDay()
    }
}