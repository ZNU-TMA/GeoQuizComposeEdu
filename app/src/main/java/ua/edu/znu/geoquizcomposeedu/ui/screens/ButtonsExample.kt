package ua.edu.znu.geoquizcomposeedu.ui.screens

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

private const val TAG = "ButtonsExample"

@Composable
fun ButtonsExample() {
    var buttonState by rememberSaveable {
        mutableStateOf(ButtonState())
    }
    Button(
        onClick = {
            buttonState = buttonState.copy(
                clickCount = buttonState.clickCount + 1,
                isButtonColorRed = !buttonState.isButtonColorRed
            )
            Log.d(TAG, "Button's color Red is ${buttonState.isButtonColorRed}")
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (buttonState.isButtonColorRed) Color.Red else Color.White,
            contentColor = buttonState.contentColor
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Click me!")
    }
    Text("Count of clicks: ${buttonState.clickCount}")
}

@Parcelize
data class ButtonState(
    val clickCount: Int = 0,
    val isButtonColorRed: Boolean = true
) : Parcelable {
    @IgnoredOnParcel
    val contentColor: Color
        get() = if (isButtonColorRed) Color.White else Color.Red
}

@Preview(showBackground = true)
@Composable
fun ButtonsExamplePreview() {
    ButtonsExample()
}
