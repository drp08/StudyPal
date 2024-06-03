package io.github.drp08.studypal.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.datetime.LocalDateTime
import network.chaintech.ui.datetimepicker.WheelDateTimePickerView
import network.chaintech.utils.DateTimePickerView
import network.chaintech.utils.MAX
import network.chaintech.utils.MIN
import network.chaintech.utils.TimeFormat
import network.chaintech.utils.WheelPickerDefaults
import network.chaintech.utils.dateTimeToString
import network.chaintech.utils.now

object AddEventScreen : Screen {
    @Composable
    override fun Content() {
        TODO()
    }

    @Composable
    fun EventNameTextField() {
        var eventName by rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = eventName,
            onValueChange = { eventName = it },
            label = { Text("Event Name") }
        )
    }

    @Composable
    fun DateTimeBox() {
        var showDateTimePicker by remember { mutableStateOf(false) }
        var dateTime by rememberSaveable { mutableStateOf("") }

        if (showDateTimePicker) {
            WheelDateTimePickerView(
                modifier = Modifier.padding(top = 18.dp, bottom = 10.dp).fillMaxWidth(),
                showDatePicker = showDateTimePicker,
                title = "Add Date/Time",
                doneLabel = "Okay",
                timeFormat = TimeFormat.HOUR_24,
                titleStyle = LocalTextStyle.current,
                doneLabelStyle = LocalTextStyle.current,
                startDate = LocalDateTime.now(),
                minDate = LocalDateTime.MIN(),
                maxDate = LocalDateTime.MAX(),
                yearsRange = IntRange(2024, 2300),
                height = 170.dp,
                rowCount = 5,
                dateTextStyle = MaterialTheme.typography.subtitle1,
                dateTextColor = LocalContentColor.current,
                hideHeader = false,
                containerColor = Color.White,
                shape = RoundedCornerShape(10.dp),
                dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
                onDoneClick = { dateTime = dateTimeToString(it, "yyyy-MM-dd hh:mm")
                              showDateTimePicker = false},
                selectorProperties = WheelPickerDefaults.selectorProperties(borderColor = Color.DarkGray),
                onDismiss = { showDateTimePicker = false}
            )
        }
    }

}