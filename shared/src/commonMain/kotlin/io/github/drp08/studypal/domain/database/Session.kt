package io.github.drp08.studypal.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Session (
    @PrimaryKey val session_id:Int,
    val start_date_time: Int,
    val end_date_time:Int
)