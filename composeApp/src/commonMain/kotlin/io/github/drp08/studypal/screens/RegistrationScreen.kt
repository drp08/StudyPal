package io.github.drp08.studypal.screens



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen


object RegistrationScreen : Screen {
    @Composable
    override fun Content() {
        var name by remember { mutableStateOf("") }
        var workingHoursStart by remember { mutableStateOf("") }
        var workingHoursEnd by remember { mutableStateOf("") }
        var hoursPerDay by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "StudyPal",
                style = MaterialTheme.typography.h3.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Your name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface
                )
            )

            Text(
                text = "I would like revision to be scheduled between",
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                style = MaterialTheme.typography.body1
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = workingHoursStart,
                    onValueChange = { workingHoursStart = it },
                    label = { Text("Start") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                Text(
                    text = "and",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.body1
                )

                TextField(
                    value = workingHoursEnd,
                    onValueChange = { workingHoursEnd = it },
                    label = { Text("End") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            }

            TextField(
                value = hoursPerDay,
                onValueChange = { hoursPerDay = it },
                label = { Text("I want to work ... Hours per day") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Handle Google Calendar import */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Import Calendar", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Handle registration */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Register", fontSize = 18.sp)
            }
        }
//        var username by remember { mutableStateOf("") }
//        var email by remember { mutableStateOf("") }
//        var password by remember { mutableStateOf("") }
//        var passwordVisible by remember { mutableStateOf(false) }
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
////            Image(
////                painter = rememberAsyncImagePainter("https://via.placeholder.com/150"),
////                contentDescription = "Logo",
////                modifier = Modifier
////                    .size(150.dp)
////                    .padding(16.dp)
////            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//
//            Text(
//                text = "Register",
//                style = MaterialTheme.typography.h4,
//                color = MaterialTheme.colors.primary,
//                modifier = Modifier.padding(bottom = 24.dp)
//            )
//
//            TextField(
//                value = username,
//                onValueChange = { username = it },
//                label = { Text("Username") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                singleLine = true,
//                shape = RoundedCornerShape(8.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    backgroundColor = MaterialTheme.colors.surface
//                )
//            )
//
//            TextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                singleLine = true,
//                shape = RoundedCornerShape(8.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    backgroundColor = MaterialTheme.colors.surface
//                )
//            )
//
//            TextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                singleLine = true,
//                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//                shape = RoundedCornerShape(8.dp),
//                colors = TextFieldDefaults.textFieldColors(
//                    backgroundColor = MaterialTheme.colors.surface
//                ),
////                trailingIcon = {
////                    val image = if (passwordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
////
////                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
////                        Icon(
////                            painter = rememberImagePainter(image),
////                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
////                        )
////                    }
////                }
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//
//            Button(
//                onClick = { /* Handle registration */ },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = "Register")
//            }
//        }
    }
}
