package io.github.drp08.studypal.domain.database

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface SessionDao {
    @Upsert
    suspend fun upsert(session: Session)

    @Delete
    suspend fun delete(session: Session)

    @Query("SELECT * FROM session")
    fun getAllSession(): Flow<List<Session>>
}