package io.github.drp08.studypal.domain.database

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface SubjectDao {
    @Upsert
    suspend fun upsert(subject:Subject)

    @Delete
    suspend fun delete(subject: Subject)

    @Query("SELECT * FROM subject")
     fun getAllSubjects():Flow<List<Subject>>
}