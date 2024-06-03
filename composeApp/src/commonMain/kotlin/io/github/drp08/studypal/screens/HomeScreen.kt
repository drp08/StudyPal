package io.github.drp08.studypal.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.github.drp08.studypal.di.appModule
import io.github.drp08.studypal.viewmodels.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.kodein.di.bindSingleton
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.subDI
import org.kodein.di.instance
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        subDI(
            parentDI = appModule,
            diBuilder = { bindSingleton { HomeViewModel(instance()) } }
        ) {
            val viewModel by rememberInstance<HomeViewModel>()
            val sessions by viewModel.sessions.collectAsState()
            val currentTime = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()).time.toSecondOfDay()

            val navigator = LocalNavigator.currentOrThrow
            var isExpanded by remember { mutableStateOf(false) }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                floatingActionButton = {
                    FloatingActionButton(onClick = {isExpanded = !isExpanded }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 24.dp, horizontal = 16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (sessions.isEmpty()) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "You don't have anything. Click the plus button",
                                modifier = Modifier.padding(all = 16.dp)
                            )
                        }
                    } else {
                        val session = sessions[0]

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp, vertical = 6.dp)
                            ) {
                                Text(text = "Next Revision/Event: ")
                                Text(
                                    text = session.name,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally),
                                    fontSize = 18.sp
                                )
                                if (session.startTime > currentTime) {
                                    Text(text = "Starts in")
                                    Countdown(
                                        from = session.startTime - currentTime,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    ) {
                                        CheckInButton(
                                            navigator,
                                            session.endTime,
                                            modifier = Modifier.align(Alignment.CenterHorizontally)
                                        )
                                    }
                                } else {
                                    CheckInButton(
                                        navigator = navigator,
                                        endTime = session.endTime,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }
                    }

                    if (sessions.size > 1)
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp, vertical = 6.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "Upcoming sessions/events today",
                                    fontSize = 18.sp
                                )

                                LazyColumn(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    items(sessions.drop(1)) { subject ->
                                        val startTime = LocalTime.fromSecondOfDay(subject.startTime)
                                        val endTime = LocalTime.fromSecondOfDay(subject.endTime)

                                        Card(
                                            modifier = Modifier
                                                .padding(horizontal = 4.dp)
                                                .fillMaxWidth()
                                                .padding(vertical = 6.dp)
                                        ) {
                                            Column(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = "${startTime.hour}:${startTime.minute} - ${endTime.hour}:${endTime.minute}"
                                                )
                                                Text(text = subject.name)
                                                Text(text = "Session 0/${subject.noTotalSessions}")
                                            }
                                        }
                                    }
                                }
                            }
                        }

                }
//                if (isExpanded) {
//                    ExpandableOptions()
//                }
            }
            if (isExpanded) {
                FilterView()
            }
        }
    }

    @Composable
    private fun Countdown(
        from: Int,
        modifier: Modifier = Modifier,
        onFinish: @Composable () -> Unit = {}
    ) {
        var timeLeft by remember { mutableStateOf(from) }
        var hasFinished by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = timeLeft) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            hasFinished = true
        }

        val time = LocalTime.fromSecondOfDay(timeLeft)

        if (!hasFinished) {
            Text(
                text = "${time.hour}:${time.minute}:${time.second}",
                modifier = modifier,
                fontSize = 18.sp
            )
        } else {
            onFinish()
        }
    }

    @Composable
    private fun CheckInButton(
        navigator: Navigator,
        endTime: Int,
        modifier: Modifier = Modifier
    ) {
        Button(
            onClick = { navigator.push(PomodoroScreen(endTime)) },
            modifier = modifier
        ) {
            Text(text = "Check-in")
        }
    }

    @Composable
    fun ExpandableOptions(
    ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.9f))
            ) {
                Column(
                    modifier = Modifier
//                        .widthIn(max = 0.33.dp)
////                        .wrapContentHeight()
                        .padding(horizontal = 16.dp)
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    ExpandableOptionButton("Edit Calendar")
                    ExpandableOptionButton("Import Calendar")
                    ExpandableOptionButton("Event")
                    ExpandableOptionButton("Study Session")
                }
            }
    }

    @Composable
    fun ExpandableOptionButton(text: String) {
        Button(
            onClick = { /* Handle option click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(text = text)
        }
    }

//    @Composable
//    fun FilterView(
//        items: List<FilterFabMenuItem>,
//        modifier: Modifier = Modifier,
//        expanded: Boolean
//    ) {
//        val transition = updateTransition(targetState = expanded)
//
//        val offsetX by transition.animateDp(
//            transitionSpec = {
//                slideInHorizontally({ width -> width }, tween())
//            }
//        ) { state ->
//            if (state) 0.dp else 300.dp
//        }
//
//        Column(
//            modifier = modifier
//                .offset(x = offsetX)
//                .padding(16.dp)
//                .fillMaxWidth()
//                .background(Color.White)) {
//            items.forEach { menuItem ->
//                FilterFabMenuItem(menuItem = menuItem)
//            }
//        }
//    }
//
//    @Composable
//    fun FilterFabMenuItem(
//        menuItem: FilterFabMenuItem,
//        modifier: Modifier = Modifier
//    ) {
//        Row(
//            modifier = modifier.padding(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(imageVector = menuItem.icon, contentDescription = null)
//            Text(text = menuItem.label, modifier = Modifier.padding(start = 8.dp))
//        }
//    }
//
//    data class FilterFabMenuItem(val label: String, val icon: ImageVector)
//
//    enum class FilterFabState { COLLAPSED, EXPANDED }

    @Composable
    fun FilterView(
        modifier: Modifier = Modifier
    ) {
        var filterFabState by remember { mutableStateOf(FilterFabState.COLLAPSED) }
        val transitionState = remember {
            MutableTransitionState(filterFabState).apply {
                targetState = FilterFabState.COLLAPSED
            }
        }

        val transition = updateTransition(targetState = transitionState, label = "transition")

        val iconRotationDegree by transition.animateFloat({
            tween(durationMillis = 150, easing = FastOutSlowInEasing)
        }, label = "rotation") {
            if (filterFabState == FilterFabState.EXPANDED) 230f else 0f
        }

        Column(
            modifier = modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom)
        ) {
            FilterFabMenu(visible = filterFabState == FilterFabState.EXPANDED)
            FilterFab(
                state = filterFabState,
                rotation = iconRotationDegree, onClick = { state ->
                    filterFabState = state
                })
        }
    }
}

enum class FilterFabState {
    EXPANDED, COLLAPSED
}

@Composable
fun FilterFabMenu(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        "Edit Calendar",
        "Import Calendar",
        "Event",
        "Study Session"
    )

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Bottom,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        )
    }

    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Bottom,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        ) + fadeOut(
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        )
    }

    AnimatedVisibility(visible = visible, enter = enterTransition, exit = exitTransition) {
        Column(
            modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items.forEach { menuItem ->
                FilterFabMenuItem(menuItem)
            }
        }
    }
}

@Composable
fun FilterFab(
    state: FilterFabState,
    rotation:Float,
    onClick: (FilterFabState) -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier
            .rotate(rotation),
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
        onClick = {
            onClick(
                if (state == FilterFabState.EXPANDED) {
                    FilterFabState.COLLAPSED
                } else {
                    FilterFabState.EXPANDED
                }
            )
        },
        backgroundColor = Color.Blue,
        shape = CircleShape
    ) {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = Color.White
        )
    }
}


@Composable
fun FilterView(
    items: List<String>,
    modifier: Modifier = Modifier
) {

    var filterFabState by rememberSaveable() {
        mutableStateOf(FilterFabState.COLLAPSED)
    }

    val transitionState = remember {
        MutableTransitionState(filterFabState).apply {
            targetState = FilterFabState.COLLAPSED
        }
    }

    val transition = updateTransition(targetState = transitionState, label = "transition")

    val iconRotationDegree by transition.animateFloat({
        tween(durationMillis = 150, easing = FastOutSlowInEasing)
    }, label = "rotation") {
        if (filterFabState == FilterFabState.EXPANDED) 230f else 0f
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.Bottom)
    ) {
        FilterFabMenu(items = items, visible = filterFabState == FilterFabState.EXPANDED)
        FilterFab(
            state = filterFabState,
            rotation = iconRotationDegree, onClick = { state ->
                filterFabState = state
            })
    }
}

@Composable
fun FilterFabMenu(
    items: List<String>,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Bottom,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        )
    }

    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Bottom,
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        ) + fadeOut(
            animationSpec = tween(150, easing = FastOutSlowInEasing)
        )
    }

    AnimatedVisibility(visible = visible, enter = enterTransition, exit = exitTransition) {
        Column(
            modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items.forEach { menuItem ->
                FilterFabMenuItem(menuItem)
            }
        }
    }
}

@Composable
fun FilterFabMenuItem(
    item: FilterFabMenuItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        backgroundColor = Color.Red
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null
        )
    }
}

data class FilterFabMenuItem(
    val label: String,
)
