package io.github.drp08.studypal.domain.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>
}
