package io.github.drp08.studypal.domain

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

fun getSubjectDatabase(context: Context): SubjectDatabase {
    val dbFile = context.getDatabasePath("subject.db")
    return Room.databaseBuilder<SubjectDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}