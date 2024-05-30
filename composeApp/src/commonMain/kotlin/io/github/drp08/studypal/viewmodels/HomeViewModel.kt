package io.github.drp08.studypal.viewmodels

import androidx.lifecycle.ViewModel
import io.github.drp08.studypal.Subject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(

) : ViewModel() {
    private val _sessions = MutableStateFlow(emptyList<Subject>())
    val sessions = _sessions.asStateFlow()

    fun addNewSession() {
        // TODO make call
    }
}