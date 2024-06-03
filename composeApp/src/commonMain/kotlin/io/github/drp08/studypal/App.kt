package io.github.drp08.studypal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import io.github.drp08.studypal.domain.database.User
import io.github.drp08.studypal.domain.database.UserDao
import io.github.drp08.studypal.navigation.BottomNavBar
import io.github.drp08.studypal.navigation.BottomNavItem
import io.github.drp08.studypal.screens.BlankScreen
import kotlinx.coroutines.launch
import kotlinx.datetime.format.Padding
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(userDao: UserDao) {
    MaterialTheme {
        val users by userDao.getAllUsers().collectAsState(initial = emptyList())
        val scope = rememberCoroutineScope()
        LaunchedEffect(true){
            val userList = listOf(
                User(name = "Harini",12,16,4,1.0),
                User(name = "Nishant",15,20,4,1.0),
                User(name = "Virginia",10,18,4,1.0),
                User(name = "Bella",17,21,4,1.0)
            )
            userList.forEach{
                userDao.upsert(it)
            }

        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(users) {
                user -> Text(
                    text = user.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{
                            scope.launch {
                                userDao.delete(user)
                            }
                        }
                        .padding(16.dp)
                )
            }
        }
//        Navigator(BlankScreen) { navigator ->
//            if (navigator.lastItem is BlankScreen) {
//                Navigator(BottomNavItem.Home.screen) { _ ->
//                    Scaffold(
//                        bottomBar = { BottomNavBar() }
//                    ) { paddingValues ->
//                        Box(modifier = Modifier.padding(paddingValues)) {
//                            CurrentScreen()
//                        }
//                    }
//                }
//            } else {
//                CurrentScreen()
//            }
//        }
    }
}
