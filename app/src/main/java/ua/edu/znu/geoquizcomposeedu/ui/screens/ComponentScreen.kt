package ua.edu.znu.geoquizcomposeedu.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ComponentScreen(innerPadding: PaddingValues) {
    var textValue: String by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
//        TextField(
        OutlinedTextField(
//            value = "qwerty",
            value = textValue,
            onValueChange = { newTextValue ->
                /* Handle text change */
                textValue = newTextValue
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ComponentScreenPreview() {
    ComponentScreen(
        innerPadding = PaddingValues(0.dp)
    )
}