package io.github.drp08.studypal.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [User::class, Subject::class, Topic::class, Session::class],
    version = 1
)
abstract class ClientDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun subjectDao() : SubjectDao
    abstract fun topicDao() : TopicDao
    abstract fun sessionDao(): SessionDao
}