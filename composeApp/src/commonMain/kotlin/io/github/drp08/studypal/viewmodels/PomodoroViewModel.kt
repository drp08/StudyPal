package io.github.drp08.studypal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class PomodoroViewModel : ViewModel() {
    val endTime = Clock.System.now().plus(10, DateTimeUnit.MINUTE).toLocalDateTime(TimeZone.currentSystemDefault())

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