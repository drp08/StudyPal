package io.github.drp08.studypal.models

import java.time.LocalTime

object Database {
    var startTime = toNearestHour()

    private fun toNearestHour(): Int {
        return LocalTime.now().plusMinutes(1).toSecondOfDay()
    }
}