package io.github.drp08.studypal.domain.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
@Dao
interface TopicDao {
    @Upsert
    suspend fun upsert(topic: Topic)

    @Delete
    suspend fun delete(topic: Topic)

    @Query("SELECT * FROM topic")
    fun getALlTopics(): Flow<List<Topic>>
}