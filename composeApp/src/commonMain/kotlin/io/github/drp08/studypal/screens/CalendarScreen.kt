package io.github.drp08.studypal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
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
import kotlinx.datetime.TimeZone

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
                text = currentDate.toString(), // Displaying date in default format
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val timeSlots = generateTimeSlots(startTime, endTime)
                items(timeSlots) { slot ->
                    val slotEvents = events.filter { event ->
                        event.startTime <= slot && event.endTime > slot
                    }
                    CalendarTimeSlot(slot.toString(), slotEvents) // Displaying time in default format
                }
            }
        }
    }

    @Composable
    fun CalendarTimeSlot(time: String, events: List<Event>) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.LightGray
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = time, fontWeight = FontWeight.Bold)
                events.forEach { event ->
                    Text(text = event.title)
                }
            }
        }
    }

    private fun generateTimeSlots(startTime: LocalTime, endTime: LocalTime): List<LocalTime> {
        val timeSlots = mutableListOf<LocalTime>()
        var current = startTime
        while (current < endTime) {
            timeSlots.add(current)
            current = addMinutesToLocalTime(current, 30)
        }
        return timeSlots
    }

    private fun addMinutesToLocalTime(time: LocalTime, minutesToAdd: Int): LocalTime {
        val totalMinutes = time.hour * 60 + time.minute + minutesToAdd
        val newHour = totalMinutes / 60
        val newMinute = totalMinutes % 60
        return LocalTime(newHour % 24, newMinute)
    }
}