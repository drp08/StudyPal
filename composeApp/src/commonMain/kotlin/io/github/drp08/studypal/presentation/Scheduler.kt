package io.github.drp08.studypal.presentation

import kotlin.random.Random

class Session(
    var subject:Subject,
    var topic: Topic,
    var startTime:Int,
    var endTime:Int
)

class Topic(
    var name:String
)
class User(
    var name:String,
    var startWorkingHours:Int,
    var endWorkingHours:Int,
    var maxNumberOfStudyHours:Int,
)

class Subject(
    var name:String,
    var confidenceLevel:Int,
    var totalNumOfSessions: Int,
    var numSessionsCompleted:Int,
    var numSessionsScheduled:Int,
    var topics: List<Topic>
)
{
    fun incrementScheduledCnt() {
        numSessionsScheduled += 1
    }
}

class Scheduler {
    fun randomiseSessions(subjects:List<Subject>, user: User) : List<Session> {
        var sessions:MutableList<Session> = mutableListOf()
        var studyHours = 0
        var scheduledHours: MutableList<Int> = mutableListOf(0) // to ensure that a time isn't 'double-booked'
        while (studyHours != user.maxNumberOfStudyHours){
            // chooses a random subject
            val subject = subjects.random()

            // chooses a random topic
            val topic = subject.topics.random()
            var time = 0
            while (scheduledHours.contains(time)){
                time = Random.nextInt(user.startWorkingHours,user.endWorkingHours)
            }
            // chooses a random start time that hasn't already been scheduled for

            scheduledHours.add(time)

            // create a new session + add to DB (TO-DO)
            sessions.add(Session(subject,topic,time,time+1))

            // increment subject session counter
            subject.incrementScheduledCnt()

            // increment number of hours studied
            studyHours += 1
        }
        return sessions.sortedBy { it.startTime }
    }
}