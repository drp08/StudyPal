package io.github.drp08.studypal.presentation.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import io.github.drp08.studypal.screens.components.ProfileSubjectCard

object ProfileScreen : Screen {
    @Composable
    override fun Content() {
        ProfileSubjectCard(
            subject = "Computer Science",
            progress = 0.5f,
            totalHours = 10,
            examDate = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}