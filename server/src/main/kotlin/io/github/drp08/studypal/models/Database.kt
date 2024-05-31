package io.github.drp08.studypal.models

import java.time.LocalTime

object Database {
    var startTime = demonstrationTime()

    private fun demonstrationTime(): Int {
        return LocalTime.now().plusSeconds(20).toSecondOfDay()
    }
}