package ua.edu.znu.geoquizcomposeedu.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ua.edu.znu.geoquizcomposeedu.R

@Composable
fun AppFloatingActionButton(onFabClick: () -> Unit) {
    FloatingActionButton(
        onClick = { /* TODO: Navigate to add question screen */ },
    ) {
        Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_question))
    }
}