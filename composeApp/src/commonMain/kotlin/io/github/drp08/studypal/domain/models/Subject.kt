package io.github.drp08.studypal.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey
    val name: String,
    val hoursPerWeek: Int,
    val examDate: Long?,
    val confidenceLevel: Int,
    val sessionsDone: Int,
    val totalSessions: Int
    // TODO a list of foreign keys to topics
)