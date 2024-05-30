package io.github.drp08.studypal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class PomodoroViewModel : ViewModel() {
    private val endTime = LocalDateTime(2024, 5, 30, 16, 30)

    private val _timeUntilBreak = MutableStateFlow(0L)
    val timeUntilBreak = _timeUntilBreak.asStateFlow()

    private val _totalSeconds = MutableStateFlow(0L)
    val totalSeconds = _totalSeconds.asStateFlow()

    init {
        tickClock()
    }

    private fun tickClock() {
        val endTimeEpoch = endTime.toInstant(TimeZone.currentSystemDefault()).epochSeconds
        val currentTime = Clock.System.now().epochSeconds

        _totalSeconds.value = endTimeEpoch - currentTime
        _timeUntilBreak.value = endTimeEpoch - currentTime

        viewModelScope.launch {
            while (timeUntilBreak.value > 0) {
                delay(1000L)
                _timeUntilBreak.value--
            }
        }
    }
}