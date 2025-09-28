package ua.edu.znu.geoquizcomposeedu.educational

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
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
    var state: Boolean by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Checkbox(
//            checked = true,
            checked = state,
            onCheckedChange = { newCheckedState ->
                /* Handle checkbox state change */
                state = newCheckedState
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