package io.github.drp08.studypal

import Greeting
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import io.github.drp08.studypal.navigation.BottomNavBar
import io.github.drp08.studypal.navigation.BottomNavItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var response by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            val greeting = Greeting()
            response = greeting.greeting().name
        }

        Navigator(BottomNavItem.Home.screen) { _ ->
            Scaffold(
                content = {
                    Text(text = response)
                },
                bottomBar = {
                    BottomNavBar()
                }
            )
        }
    }
}
