package io.github.drp08.studypal.domain

import io.github.drp08.studypal.domain.models.Subject

interface SchedulingRepository {
    suspend fun getScheduleForSubject(subject: String): Subject
}