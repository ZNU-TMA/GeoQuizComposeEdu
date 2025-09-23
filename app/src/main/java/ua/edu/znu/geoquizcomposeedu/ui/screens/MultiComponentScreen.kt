package ua.edu.znu.geoquizcomposeedu.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ua.edu.znu.geoquizcomposeedu.ui.components.Container

@Composable
fun MultiComponentScreen(innerPadding: PaddingValues) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Container("Buttons Example") {
            ButtonsExample()
        }
        Container("TextFields Example") {
            TextFieldExample()
        }
        Container("CheckBox Example") {
            CheckBoxesExample()
        }
    }
}
