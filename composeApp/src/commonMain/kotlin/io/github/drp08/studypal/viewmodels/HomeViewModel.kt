package io.github.drp08.studypal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.drp08.studypal.domain.SchedulingRepository
import io.github.drp08.studypal.domain.models.Subject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val schedulingRepository: SchedulingRepository
) : ViewModel() {
    private val _sessions = MutableStateFlow(emptyList<Subject>())
    val sessions = _sessions.asStateFlow()

    fun addNewSession() {
        viewModelScope.launch {
            val subject = schedulingRepository.getScheduleForSubject("Chemistry")

            _sessions.value = sessions.value + subject
        }
    }
}