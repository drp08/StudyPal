package io.github.drp08.studypal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.drp08.studypal.domain.SubjectDatabase
import io.github.drp08.studypal.domain.daos.SubjectDao
import io.github.drp08.studypal.domain.getSubjectDatabase
import io.github.drp08.studypal.presentation.App
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.compose.withDI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            withDI(
                di = DI.lazy {
                    bindSingleton<SubjectDao> {
                        getSubjectDatabase(applicationContext).subjectDao()
                    }
                }
            ) {
                App()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}