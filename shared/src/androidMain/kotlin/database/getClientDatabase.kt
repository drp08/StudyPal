package database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.github.drp08.studypal.domain.database.ClientDatabase

fun getClientDatabase(context: Context): ClientDatabase {
    val dbFile = context.getDatabasePath("users.db")
    return Room.databaseBuilder<ClientDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}