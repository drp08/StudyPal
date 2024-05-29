package io.github.drp08.studypal.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun BottomNavBar() {
    val navigator = LocalNavigator.currentOrThrow

    BottomNavigation(
        backgroundColor = Color.Blue,
        contentColor = Color.White
    ) {
        BottomNavItem.values().forEach {
            BottomNavigationItem(
                icon = { Icon(it.icon, contentDescription = null) },
                label = { Text(it.label) },
                onClick = { navigator.push(it.screen) },
                selected = false
            )
        }
    }
}