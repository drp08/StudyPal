package io.github.drp08.studypal.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.drp08.studypal.di.AppModule
import io.github.drp08.studypal.viewmodels.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = viewModel { HomeViewModel(AppModule.schedulingRepository) }
        val sessions by viewModel.sessions.collectAsState()
        val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time.toSecondOfDay()

        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(onClick = { viewModel.addNewSession() }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (sessions.isEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "You don't have anything. Click the plus button",
                            modifier = Modifier.padding(all = 16.dp)
                        )
                    }
                } else {
                    val session = sessions[0]

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 6.dp)
                        ) {
                            Text(text = "Next Revision/Event: ")
                            Text(
                                text = session.name,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 18.sp
                            )
                            if (session.startTime > currentTime) {
                                Text(text = "Starts in")
                                Countdown(
                                    from = session.startTime - currentTime,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            } else {
                                Button(onClick = { navigator.push(PomodoroScreen) }) {
                                    Text(text = "Check-in")
                                }
                            }
                        }
                    }
                }

                if (sessions.size > 1)
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 6.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Upcoming sessions/events today",
                                fontSize = 18.sp
                            )

                            LazyColumn(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(sessions.drop(1)) { subject ->
                                    val startTime = LocalTime.fromSecondOfDay(subject.startTime)
                                    val endTime = LocalTime.fromSecondOfDay(subject.endTime)

                                    Card(
                                        modifier = Modifier
                                            .padding(horizontal = 4.dp)
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text("${startTime.hour}:${startTime.minute} - ${endTime.hour}:${endTime.minute}")
                                            Text("Subject ${subject.name}")
                                            Text("Session 0/${subject.noTotalSessions}")
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }

    @Composable
    private fun Countdown(from: Int, modifier: Modifier = Modifier) {
       var timeLeft by remember { mutableStateOf(from) }

        LaunchedEffect(key1 = timeLeft) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
        }

        val time = LocalTime.fromSecondOfDay(timeLeft)

        Text(
            text = "${time.hour}:${time.minute}:${time.second}",
            modifier = modifier,
            fontSize = 18.sp
        )
    }
}
