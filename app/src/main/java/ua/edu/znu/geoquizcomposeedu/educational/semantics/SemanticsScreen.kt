package ua.edu.znu.geoquizcomposeedu.educational.semantics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SemanticsScreen() {
    SubSection(title = "Semantics in Jetpack Compose")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        /* The embedded semantic properties */
        Text(text = "Semantics Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "Info Icon"
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
//                .semantics { testTag = "SecondSpacer" })
                .testTag("SecondSpacer"))
        /* Additional semantic properties can be added here */
        /* For example, you can add a Switch with a Role and a State description */
        var checked by remember { mutableStateOf(true) }
        Switch(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = Modifier.semantics {
                role = Role.Switch
                this.stateDescription = if (checked) "On" else "Off"
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        val snackbarHostState = remember { SnackbarHostState() }
        /* SnackbarHost shows nothing until a message is requested */
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar(
                message = "SnackBar Message",
                duration = SnackbarDuration.Indefinite
            )
        }
        /* Add semantics for the SnackBar */
        AppSnackbar(snackbarHostState)
        Spacer(modifier = Modifier.height(16.dp))
        ShareSheet(message = "Share this message")
        Spacer(modifier = Modifier.height(16.dp))
        Error(
            errorText = "Fields cannot be empty",
            modifier = Modifier
                .semantics {
                    error("Please add both email and password")
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProgressInfoBar(
            modifier = Modifier
                .semantics {
                    progressBarRangeInfo =
                        ProgressBarRangeInfo(
                            current = 0.5F,
                            range = 0F..1F
                        )
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        val milkyWay = List(10) { it.toString() }
        MilkyWayList(
            modifier = Modifier
                .semantics {
                    collectionInfo = CollectionInfo(
                        rowCount = milkyWay.count(),
                        columnCount = 1
                    )
                }
        ) {
            milkyWay.forEachIndexed { index, text ->
                Text(
                    text = text,
                    modifier = Modifier.semantics {
                        collectionItemInfo =
                            CollectionItemInfo(index, 0, 0, 0)
                    }
                )
            }
        }
    }
}

@Composable
fun SubSection(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        /* Add semantics for the heading */
        modifier = Modifier
            .padding(vertical = 8.dp)
            .semantics {
                heading()
            }
    )
}

@Composable
fun AppSnackbar(snackbarHostState: SnackbarHostState) {
    SnackbarHost(snackbarHostState) {
        Snackbar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ) {
            Text(
                text = "SnackBar Message",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .semantics {
                        liveRegion = LiveRegionMode.Polite
                    }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareSheet(message: String, modifier: Modifier = Modifier) {
    ModalBottomSheet(
        onDismissRequest = { /* Handle dismiss */ },
        modifier = modifier
    ) {
        Column(modifier = Modifier.semantics { paneTitle = "New bottom sheet" }) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun Error(
    errorText: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = errorText,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
private fun ProgressInfoBar(
    modifier: Modifier = Modifier,
) {
    // The caller's modifier supplies the progressBarRangeInfo semantics
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    )
}

@Composable
private fun MilkyWayList(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun SemanticsScreenPreview() {
    SemanticsScreen()
}
