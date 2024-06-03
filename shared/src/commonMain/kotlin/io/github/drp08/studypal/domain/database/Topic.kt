package io.github.drp08.studypal.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Topic (
    @PrimaryKey val name:String
)