package io.github.drp08.studypal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import io.github.drp08.studypal.domain.database.UserDao
import io.github.drp08.studypal.navigation.BottomNavBar
import io.github.drp08.studypal.navigation.BottomNavItem
import io.github.drp08.studypal.screens.BlankScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(userDao: UserDao) {
    MaterialTheme {
        Navigator(BlankScreen) { navigator ->
            if (navigator.lastItem is BlankScreen) {
                Navigator(BottomNavItem.Home.screen) { _ ->
                    Scaffold(
                        bottomBar = { BottomNavBar() }
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            CurrentScreen()
                        }
                    }
                }
            } else {
                CurrentScreen()
            }
        }
    }
}
