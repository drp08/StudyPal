package io.github.drp08.studypal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import database.getClientDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = getClientDatabase(applicationContext)
        setContent {
            App(dao.userDao())
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}