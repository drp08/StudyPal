package io.github.drp08.studypal.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject (
    @PrimaryKey val name:String,
    val exam_date:Int,
    val confidence_level:Int,
    val hours_per_week:Int,
    val total_sessions:Int,
    val no_sessions_finished:Int
)