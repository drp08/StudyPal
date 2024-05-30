package io.github.drp08.studypal.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import io.github.drp08.studypal.viewmodels.PomodoroViewModel

object PomodoroScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = viewModel<PomodoroViewModel>()
        val text by viewModel.timer.collectAsState()

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {}
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(1f),
                        progress = 0.35f,
                        strokeWidth = 12.dp,
                        strokeCap = StrokeCap.Round
                    )

                    Text(
                        text = text.toString(),
                        fontSize = 32.sp
                    )
                }
            }
        }
    }
}