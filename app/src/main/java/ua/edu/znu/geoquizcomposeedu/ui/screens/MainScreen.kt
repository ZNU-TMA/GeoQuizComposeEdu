package ua.edu.znu.geoquizcomposeedu.ui.screens

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.util.logCompositionLifecycle
import ua.edu.znu.geoquizcomposeedu.model.Question

private const val TAG = "MainScreen"

@Parcelize
data class MainScreenState(
    val currentIndex: Int = 0
) : Parcelable  // If state content are primitive types or String,
// you can implement Parcelable instead of Serializable
// Parcelable is more efficient than Serializable
// to save and restore state in Bundle

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
) {
    val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    // Save state in the Bundle across configuration changes
    var mainScreenState by rememberSaveable { mutableStateOf(MainScreenState()) }

    logCompositionLifecycle("MainScreen")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            // Use delegate getter for state access
            text = stringResource(id = questionBank[mainScreenState.currentIndex].textResId),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
        Row(
            modifier = Modifier.size(height = 90.dp, width = 200.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(stringResource(id = R.string.true_button))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(stringResource(id = R.string.false_button))
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = {
            // Use delegate getter and setter for state access
            mainScreenState =
                mainScreenState.copy((mainScreenState.currentIndex + 1) % questionBank.size)
            // Use delegate getter for state access
            Log.d(TAG, "MainScreen: currentIndex = ${mainScreenState.currentIndex}")
        }) {
            Text(stringResource(id = R.string.next_button))
        }
        Box(
            modifier = Modifier.height(100.dp)
        ) {
            if(mainScreenState.currentIndex == questionBank.size -1) {
                logCompositionLifecycle("LastQuestionText")
                Text(
                    text = "This is the last question"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    val innerPadding = PaddingValues(16.dp)

    MainScreen(innerPadding)
}