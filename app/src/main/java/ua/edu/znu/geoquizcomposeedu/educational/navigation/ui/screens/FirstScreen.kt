package ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FirstScreen(
    onNavigateForward: (String) -> Unit
) {
    // remember is used to remember the state of the custom primitive
    val customPrimitive = remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            // To hide virtual keyboard when tapping outside TextField
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    localFocusManager.clearFocus()
                })
            }
    ) {
        Text("First Screen")
        Spacer(modifier = Modifier.height(16.dp))
        // TextField is used to enter the custom primitive
        TextField(
            value = customPrimitive.value,
            onValueChange = { customPrimitive.value = it },
            label = { Text("Enter custom primitive") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onNavigateForward(customPrimitive.value) }) {
            Text("Go forward")
        }
    }
}

@Preview
@Composable
fun FirstScreenPreview() {
    FirstScreen(onNavigateForward = {})
}