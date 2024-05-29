package io.github.drp08.studypal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
        // Widget for current session/ next session

        // Widget for upcoming sessions
        Box {

            val scrollState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            Text(text = "Upcoming sessions/events today",modifier = Modifier.padding(45.dp))
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
                    .padding(75.dp)
            ) {
                items(5) {
                    Card(
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    ) {
                        // need to fix padding
                        Column(
                            modifier = Modifier.padding(15.dp)
                                .background(color = Color(0xFF98A9D1))
                        ) {
                            Text("10:00 - 12:00")
                            Text("Subject $it")
                        }
                        Column(
                            modifier = Modifier.padding(75.dp)
                                .background(color = Color(0xFFFFD3D3))
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