package io.github.drp08.studypal.screens.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant

@Composable
fun ProfileSubjectCard(
    subject: String,
    @FloatRange(from = 0.0, to = 1.0)
    progress: Float,
    totalHours: Int,
    examDate: Instant?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = subject)
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .height(IntrinsicSize.Min)
                        .padding(start = 16.dp),
                    strokeCap = StrokeCap.Round
                )
            }

            Text(text = "Total Hours: $totalHours")

            examDate?.let {
                Text(text = "Exam Date: $it")
            }

            // TODO Add expandable panel
        }
    }
}