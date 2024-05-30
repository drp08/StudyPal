package io.github.drp08.studypal

import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    val name: String,
    val startTime: Int,
    val endTime: Int,
    val noTotalSessions: Int
)