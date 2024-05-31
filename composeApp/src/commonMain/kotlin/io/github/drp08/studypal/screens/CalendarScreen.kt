package io.github.drp08.studypal.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object CalendarScreen : Screen {
    @Composable
    override fun Content() {
        Text(text = "Calendar Screen - Testing for deployment")
    }
}