package io.github.drp08.studypal.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val name:String,
    val start_working_hours:Int,
    val end_working_hours:Int,
    val amount_of_study_hours_per_day:Int,
    val max_session_length_in_hours:Float
)