package io.github.drp08.studypal.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.drp08.studypal.domain.SchedulingRepository
import io.github.drp08.studypal.presentation.Topic
import io.github.drp08.studypal.presentation.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random
import io.github.drp08.studypal.presentation.Scheduler
import io.github.drp08.studypal.presentation.Session


class HomeViewModel(
    private val schedulingRepository: SchedulingRepository
) : ViewModel() {
    private var subjects = mutableListOf (
        io.github.drp08.studypal.presentation.Subject(
            "Subject 1",
            3,
            5,
            2,
            2,
            mutableListOf(
                Topic("Topic 1"),
                Topic("Topic 2"),
                Topic("Topic 3"),
                Topic("Topic 4")
            )
        ),
        io.github.drp08.studypal.presentation.Subject(
            "Subject 2",
            3,
            5,
            2,
            0,
            mutableListOf(
                Topic("Topic 1"),
                Topic("Topic 2"),
                Topic("Topic 3"),
                Topic("Topic 4")
            )
        ),
        io.github.drp08.studypal.presentation.Subject(
            "Subject 3",
            3,
            5,
            2,
            2,
            mutableListOf(
                Topic("Topic 1"),
                Topic("Topic 2"),
                Topic("Topic 3"),
                Topic("Topic 4")
            )
        )
    )
    private var user = User("Harini",10,20,5)
    fun getSessions() : List<Session>{
        return Scheduler().randomiseSessions(subjects,user)
    }
//    private val _sessions = MutableStateFlow(emptyList<Subject>())
//    val sessions = _sessions.asStateFlow()
//
//    private val subjects = listOf("Chemistry", "Computer Science", "Mathematics", "Biology", "Physics",
//        "Economics", "Accounting", "History", "Geography", "Sociology", "Literature", "Psychology")
//
//    fun addNewSession() {
//        viewModelScope.launch {
//            val randomIndex = Random.nextInt(subjects.size)
//            val randomSubject = subjects[randomIndex]
//
//            val subject = schedulingRepository.getScheduleForSubject(randomSubject)
//
//            _sessions.value = sessions.value + subject
//        }
//    }

}