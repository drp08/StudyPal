package io.github.drp08.studypal.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.drp08.studypal.di.appModule
import io.github.drp08.studypal.presentation.viewmodels.HomeViewModel
import io.github.drp08.studypal.screens.components.fab.ExpandableFab
import io.github.drp08.studypal.screens.components.fab.FabItem
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.kodein.di.bindSingleton
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI
import org.kodein.di.instance

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        subDI(
            parentDI = appModule,
            diBuilder = { bindSingleton { HomeViewModel(instance()) } }
        ) {
            val viewModel by rememberInstance<HomeViewModel>()
            val sessions = viewModel.getSessions()
            val currentTime = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()).time.toSecondOfDay()

            val navigator = LocalNavigator.currentOrThrow

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                floatingActionButton = {
                    ExpandableFab(
                        items = listOf(FabItem("Subject"), FabItem("Event"))
                    )
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
                                    text = "${session.subject.name} : ${session.topic.name}",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally),
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "${session.startTime} : ${session.endTime}",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally),
                                    fontSize = 18.sp
                                )
                                if (session.startTime > currentTime) {
                                    Text(text = "Starts in")
                                    Countdown(
                                        from = session.startTime - currentTime,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    ) {
                                        CheckInButton(
                                            navigator,
                                            session.endTime,
                                            modifier = Modifier.align(Alignment.CenterHorizontally)
                                        )
                                    }
                                } else {
                                    CheckInButton(
                                        navigator = navigator,
                                        endTime = session.endTime,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
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
                                    items(sessions.drop(1)) { session ->
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
                                                Text(
                                                    text = "${session.startTime} to ${session.endTime}"
                                                )
                                                Text(text = "${session.subject.name} : ${session.topic.name}")
                                                Text(text = "Session 0/${session.subject.totalNumOfSessions}")
                                            }
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
    private fun Countdown(
        from: Int,
        modifier: Modifier = Modifier,
        onFinish: @Composable () -> Unit = {}
    ) {
        var timeLeft by remember { mutableStateOf(from) }
        var hasFinished by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = timeLeft) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            hasFinished = true
        }

        val time = LocalTime.fromSecondOfDay(timeLeft)

        if (!hasFinished) {
            Text(
                text = "${time.hour}:${time.minute}:${time.second}",
                modifier = modifier,
                fontSize = 18.sp
            )
        } else {
            onFinish()
        }
    }

    @Composable
    private fun CheckInButton(
        navigator: Navigator,
        endTime: Int,
        modifier: Modifier = Modifier
    ) {
        Button(
            onClick = { navigator.push(PomodoroScreen(endTime)) },
            modifier = modifier
        ) {
            Text(text = "Check-in")
        }
    }
}