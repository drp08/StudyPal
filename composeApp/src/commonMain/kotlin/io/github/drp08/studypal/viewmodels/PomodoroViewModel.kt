package io.github.drp08.studypal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PomodoroViewModel : ViewModel() {

    private val _timer = MutableStateFlow(0)
    val timer = _timer.asStateFlow()

    fun startTimer(from: Int) {
        _timer.value = from

        viewModelScope.launch {
            while (true) {
                delay(1000)
                _timer.value--

                if (_timer.value <= 0)
                    break
            }
        }
    }
}