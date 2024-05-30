package io.github.drp08.studypal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val BLUE = Color(0xFF98A9D1)
        val PINK = Color(0xFFFFD3D3)
        val navigator = LocalNavigator.currentOrThrow
        // Widget for current session/ next session
        Box {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentSize(align = Alignment.TopCenter)
                        .padding(vertical = 10.dp).background(color = BLUE)
                        .clickable(onClick = {navigator.push(ProfileScreen)}) // will route to the Pomodoro screen once completed
                ) {
                    Text("Next Session starting in: 00:32:17")
                }
        }
        // Widget for upcoming sessions
        Box {
            val scrollState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            Column {
            Text(text = "Upcoming sessions/events today",modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).padding(vertical = 70.dp))
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentSize(align = Alignment.Center)
                        .draggable(
                            orientation = Orientation.Vertical,
                            state = rememberDraggableState { delta ->
                                coroutineScope.launch {
                                    scrollState.scrollBy(-delta)
                                }
                            },
                        )
                ) {
                    items(5) {
                        Card(
                            modifier = Modifier
                                .wrapContentSize(align = Alignment.Center)
                        ) {
                            Column {
                                Column(
                                    modifier = Modifier.padding(15.dp)
                                        .background(color = PINK)
                                ) {
                                    Text("10:00 - 12:00")
                                    Text("Subject ${it + 1}")
                                }
                                Column(
                                    modifier = Modifier.padding(15.dp)
                                        .background(color = BLUE)
                                ) {
                                    Text("13:00 - 15:00")
                                    Text("Subject ${5 - it}")
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}