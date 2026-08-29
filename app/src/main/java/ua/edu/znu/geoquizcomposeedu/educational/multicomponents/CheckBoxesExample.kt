package ua.edu.znu.geoquizcomposeedu.educational.multicomponents

import android.os.Parcelable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.parcelize.Parcelize

data class CheckableItem(
    val title: String,
    var isChecked: MutableState<Boolean>
)

data class CheckBoxesState(
    val checkableItems: List<CheckableItem>
) {
    val selectedItemNames: String
        get() {
            return checkableItems.asSequence()
                .filter { it.isChecked.value }
                .map { it.title }
                .joinToString()
                .takeIf { it.isNotEmpty() } ?: "[nothing]"
        }

    companion object {
        val Saver: Saver<CheckBoxesState, *> = Saver(
            save = { state: CheckBoxesState ->
                ParcelableCheckBoxesState(
                    checkedItems = state.checkableItems.map {
                        ParcelableCheckedItem(
                            title = it.title,
                            isChecked = it.isChecked.value
                        )
                    }
                )
            },
            restore = { state: ParcelableCheckBoxesState ->
                CheckBoxesState(
                    checkableItems = state.checkedItems.map {
                        CheckableItem(
                            title = it.title,
                            isChecked = mutableStateOf(it.isChecked)
                        )
                    }
                )
            }
        )
    }
}

@Parcelize
data class ParcelableCheckedItem(
    val title: String,
    val isChecked: Boolean
) : Parcelable

@Parcelize
data class ParcelableCheckBoxesState(
    val checkedItems: List<ParcelableCheckedItem>
) : Parcelable

@Composable
fun CheckBoxesExample() {
    val state: CheckBoxesState = rememberSaveable(
        saver = CheckBoxesState.Saver
    ) {
        CheckBoxesState(
            checkableItems = List(6) { index ->
                val id = index + 1
                CheckableItem(
                    "Item $id",
                    mutableStateOf(false)
                )
            }
        )
    }
    state.checkableItems.forEach { checkedItem ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        val newCheckValue = !checkedItem.isChecked.value
                        checkedItem.isChecked.value = newCheckValue
                    },
                    interactionSource = MutableInteractionSource(),
                    indication = ripple(),
                )
        ) {
            Checkbox(
                checked = checkedItem.isChecked.value,
                onCheckedChange = {
                    val newCheckValue = !checkedItem.isChecked.value
                    checkedItem.isChecked.value = newCheckValue
                },
            )
            Text(text = checkedItem.title)
        }
    }
    Text("Selected items: ${state.selectedItemNames}")
}

@Preview(showBackground = true)
@Composable
fun CheckBoxesExamplePreview() {
    CheckBoxesExample()
}