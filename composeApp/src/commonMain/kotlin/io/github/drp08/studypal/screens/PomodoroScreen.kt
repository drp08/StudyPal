package io.github.drp08.studypal.screens

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import io.github.drp08.studypal.viewmodels.PomodoroViewModel

object PomodoroScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = viewModel<PomodoroViewModel>()
        val from = 10

        val current by viewModel.timer.collectAsState()
        val animatedCurrent by animateFloatAsState(
            targetValue = current.toFloat(),
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )

        LaunchedEffect(Unit) {
            viewModel.startTimer(from)
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
                    Text(text = "Session finishes at 9:30", modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(), textAlign = TextAlign.Center)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(1f),
                            progress = (animatedCurrent / from),
                            strokeWidth = 12.dp,
                            strokeCap = StrokeCap.Round
                        )

                        Text(
                            text = current.toString(),
                            fontSize = 32.sp
                        )
                    }
                    Text(text = "Until Break", modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(), textAlign = TextAlign.Center)
                }
            }
        }
    }
}