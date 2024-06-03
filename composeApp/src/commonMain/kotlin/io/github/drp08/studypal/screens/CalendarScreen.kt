package io.github.drp08.studypal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.datetime.*

object CalendarScreen : Screen {

    data class Event(val startTime: LocalTime, val endTime: LocalTime, val title: String)

    @Composable
    override fun Content() {
        val startTime = LocalTime(8, 0) // Start time
        val endTime = LocalTime(18, 0) // End time
        val events = listOf(
            Event(LocalTime(9, 0), LocalTime(10, 0), "Meeting 1"),
            Event(LocalTime(11, 0), LocalTime(12, 30), "Lunch"),
            Event(LocalTime(13, 0), LocalTime(15, 0), "Meeting 2"),
            Event(LocalTime(15, 30), LocalTime(17, 0), "Workshop")
        )

        DailyCalendarView(startTime, endTime, events)
    }

    @Composable
    fun DailyCalendarView(startTime: LocalTime, endTime: LocalTime, events: List<Event>) {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "${currentDate.dayOfWeek}, ${currentDate.month} ${currentDate.dayOfMonth}, ${currentDate.year}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val timeSlots = generateTimeSlots(startTime, endTime)

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    items(timeSlots) { slot ->
                        TimeSlotRow(slot, events)
                    }
                }
            }
        }
    }

    @Composable
    fun TimeSlotRow(time: LocalTime, events: List<Event>) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.LightGray.copy(alpha = 0.3f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = time.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp).weight(1f)
            )

            val event = events.find { it.startTime.hour == time.hour }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(if (event != null) Color.Blue.copy(alpha = 0.7f) else Color.Transparent)
            ) {
                if (event != null) {
                    Text(text = event.title, color = Color.White, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }

    private fun generateTimeSlots(startTime: LocalTime, endTime: LocalTime): List<LocalTime> {
        val timeSlots = mutableListOf<LocalTime>()
        var current = startTime
        while (current <= endTime) {
            timeSlots.add(current)
            current = current.plusMinutes(60) // Add 1 hour
        }
        return timeSlots
    }

    private fun LocalTime.plusMinutes(minutes: Int): LocalTime {
        val totalMinutes = hour * 60 + minute + minutes
        val newHour = totalMinutes / 60
        val newMinute = totalMinutes % 60
        return LocalTime(newHour, newMinute)
    }
}