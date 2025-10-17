package ua.edu.znu.geoquizcomposeedu.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ua.edu.znu.geoquizcomposeedu.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeoQuizTopAppBar() {
//    TopAppBar(
    CenterAlignedTopAppBar(
//    MediumTopAppBar(
//    LargeTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            /* Its possible to set custom color for icons, but its not recommended */
            navigationIconContentColor = Color.Red,
            actionIconContentColor = Color.Blue
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
}