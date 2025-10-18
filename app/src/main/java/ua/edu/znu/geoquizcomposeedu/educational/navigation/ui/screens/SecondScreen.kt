package ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(
    // custom primitive is passed as a parameter from FirstScreen
    customPrimitive: String,
    onNavigateBack: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // custom primitive is displayed on the screen
        Text("Second Screen - $customPrimitive")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateBack) {
            Text("Go back")
        }
    }
}

@Preview
@Composable
fun SecondScreenPreview() {
    SecondScreen(customPrimitive = "Custom primitive", onNavigateBack = {})
}