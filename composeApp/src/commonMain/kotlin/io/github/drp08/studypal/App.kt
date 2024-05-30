package io.github.drp08.studypal

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import io.github.drp08.studypal.navigation.BottomNavBar
import io.github.drp08.studypal.navigation.BottomNavItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(BottomNavItem.Home.screen) { _ ->
            Scaffold(
                content = {},
                bottomBar = {
                    BottomNavBar()
                }
            )
        }
    }
}
