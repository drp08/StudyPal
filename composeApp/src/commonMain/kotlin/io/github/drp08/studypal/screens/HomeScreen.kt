package io.github.drp08.studypal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import kotlinx.coroutines.launch

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val BLUE = Color(0xFF98A9D1)
        val PINK = Color(0xFFFFD3D3)
        // Widget for current session/ next session
        Box {
            Column(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.TopCenter).padding(vertical = 10.dp).background(color = BLUE)) {
                Text("Next Session starting in:")
                Text("00:32:17")
            }
        }
        // Widget for upcoming sessions
        Box {
            val scrollState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            Column(modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.Center).padding(vertical = 75.dp)) {
            Text(text = "Upcoming sessions/events today")
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
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
                            Column(
                                modifier = Modifier.padding(15.dp)
                                    .background(color = PINK)
                            ) {
                                Text("10:00 - 12:00")
                                Text("Subject $it")
                            }
                            Column(
                                modifier = Modifier.padding(75.dp)
                                    .background(color = BLUE)
                            ) {
                                Text("12:00 - 14:00")
                                Text("Subject $it")
                            }
                        }
                    }
                }
            }
        }
    }
}