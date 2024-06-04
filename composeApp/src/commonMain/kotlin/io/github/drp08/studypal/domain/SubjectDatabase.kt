package io.github.drp08.studypal.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.drp08.studypal.domain.daos.SubjectDao
import io.github.drp08.studypal.domain.models.Subject

@Database(
    entities = [Subject::class],
    version = 1
)
abstract class SubjectDatabase: RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
}