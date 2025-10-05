package ua.edu.znu.geoquizcomposeedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ua.edu.znu.geoquizcomposeedu.ui.screens.MainScreen
import ua.edu.znu.geoquizcomposeedu.ui.theme.GeoQuizComposeEduTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizComposeEduTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = stringResource(R.string.app_name),
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            navigationIcon = {
                                IconButton(
                                    onClick = { /* Handle navigation icon press */ }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = stringResource(R.string.main_menu)
                                    )
                                }
                            },
                            actions = {
                                IconButton(
                                    onClick = { /* Handle action icon press */ }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = stringResource(R.string.more_actions)
                                    )
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { /* Handle FAB click */ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(R.string.add_question)
                            )
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                selected = true,
                                label = { Text(stringResource(R.string.questions)) },
                                onClick = { /* Handle menu click */ },
                                icon = {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.List,
                                        contentDescription = stringResource(R.string.questions)
                                    )
                                },
                            )
                            NavigationBarItem(
                                selected = false,
                                label = { Text(stringResource(R.string.settings)) },
                                onClick = { /* Handle menu click */ },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = stringResource(R.string.settings)
                                    )
                                },
                            )
                            NavigationBarItem(
                                selected = false,
                                label = { Text(stringResource(R.string.profile)) },
                                onClick = { /* Handle menu click */ },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.AccountBox,
                                        contentDescription = stringResource(R.string.profile)
                                    )
                                },
                            )
                        }
                    },
                    snackbarHost = {
                        SnackbarHost(snackbarHostState) { data ->
                            Snackbar(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                            ) {
                                Text(
                                    text = data.visuals.message,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                                )
                            }
                        }
                    },
                ) { innerPadding ->
                    // main content
                    MainScreen(
                        innerPadding = innerPadding,
                        snackbarHostState = snackbarHostState
                    )
//                    ComponentScreen(innerPadding)
//                    MultiComponentScreen(innerPadding)
//                    SimpleListExample(innerPadding)
//                    UserListExample(innerPadding)
//                    QuestionListScreen(innerPadding)
                }
            }
        }
    }
}
