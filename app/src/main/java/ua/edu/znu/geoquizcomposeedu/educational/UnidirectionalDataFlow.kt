package ua.edu.znu.geoquizcomposeedu.educational

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

//@Composable
//fun UniDirectionalDataFlow(
//    innerPadding: PaddingValues
//) {
//    // Parent component holds the single source of truth
//    val isCheckedState = remember { mutableStateOf(false) }
//
//    // Event callback to handle state changes
//    val onCheckedChange = { newValue: Boolean ->
//        isCheckedState.value = newValue
//    }
//
//    CheckBoxItemWrong()
//
//    CheckBoxItemRight(
//        // Passing state downward
//        isCheckedState = isCheckedState,
//        // Passing event for state change handling upward
//        onCheckedChange = onCheckedChange
//    )
//}

//@Composable
//fun CheckBoxItemWrong(
//    // Problem: CheckBoxItem has a default MutableState parameter,
//    // so it can create and own its own state (making it uncontrolled).
//    // UDF requires the parent to hold the single source of truth
//    // and pass a value + event callback downward.
//    isCheckedState: MutableState<Boolean> = mutableStateOf(false)
//) {
//    Checkbox(
//        // Problem: CheckBoxItem receives state from itself
//        checked = isCheckedState.value,
//        // Problem: CheckBoxItem changes its own state directly,
//        // so the parent is not notified of changes. This breaks UDF.
//        onCheckedChange = { newValue ->
//            isCheckedState.value = newValue
//        }
//    )
//}

//@Composable
//fun CheckBoxItemRight(
//    // Correct: CheckBoxItem receives state from the parent
//    isCheckedState: MutableState<Boolean>,
//    // Correct: CheckBoxItem notifies the parent of state changes via callback
//    onCheckedChange: (Boolean) -> Unit
//) {
//    Checkbox(
//        // Correct: CheckBoxItem receives state from the parent
//        checked = isCheckedState.value,
//        // Correct: CheckBoxItem notifies the parent of state changes via callback
//        onCheckedChange = onCheckedChange
//    )
//}


//@Preview
//@Composable
//fun UniDirectionalDataFlowPreview() {
//    UniDirectionalDataFlow(
//        innerPadding = PaddingValues()
//    )
//}
