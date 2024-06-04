package io.github.drp08.studypal.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class PomodoroScreen(
    private val endTime: Int
) : Screen {
    @Composable
    override fun Content() {
        val current = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time.toSecondOfDay()
        val totalTimeLeft = endTime - current - 10 // 10 minute break, hardcoded
        var timeLeft by remember { mutableStateOf(totalTimeLeft) }

        LaunchedEffect(key1 = timeLeft) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = {}) {
                            Text(text = "Pomodoro short break")
                        }
                        Spacer(modifier = Modifier.width(24.dp))
                        Button(onClick = {}) {
                            Text(text = "Pomodoro long break")
                        }
                    }

                    val end = LocalTime.fromSecondOfDay(endTime)
                    Text(
                        text = "Session finishes at ${end.hour}:${end.minute}",
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(1f),
                            progress = (timeLeft.toFloat() / totalTimeLeft),
                            strokeWidth = 12.dp,
                            strokeCap = StrokeCap.Round
                        )

                        val remainingTime = LocalTime.fromSecondOfDay(timeLeft)
                        Text(
                            text = "${remainingTime.minute}:${remainingTime.second}",
                            fontSize = 32.sp
                        )
                    }
                    Text(
                        text = "Until Break",
                        modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}