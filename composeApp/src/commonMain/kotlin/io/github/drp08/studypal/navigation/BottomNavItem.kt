package io.github.drp08.studypal.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen
import io.github.drp08.studypal.screens.CalendarScreen
import io.github.drp08.studypal.screens.HomeScreen
import io.github.drp08.studypal.screens.ProfileScreen
import io.github.drp08.studypal.screens.RegistrationScreen

sealed class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val screen: Screen,
) {
    companion object {
        fun values() = listOf(Home, Calendar, Profile, Register)
    }

    data object Home : BottomNavItem("Home", Icons.Default.Home, HomeScreen)
    data object Calendar : BottomNavItem("Calendar", Icons.Default.DateRange, CalendarScreen)
    data object Profile : BottomNavItem("Profile", Icons.Default.Person, ProfileScreen)
    data object Register : BottomNavItem("Registration", Icons.Default.Settings, RegistrationScreen)
}