package ua.edu.znu.geoquizcomposeedu.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ua.edu.znu.geoquizcomposeedu.R

@Composable
fun BottomAppBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { /* Handle menu click */ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(R.string.home)
                )
            },
            label = { Text(stringResource(R.string.home)) },
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Handle menu click */ },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = stringResource(R.string.questions)
                )
            },
            label = { Text(stringResource(R.string.questions)) },
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Handle menu click */ },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = stringResource(R.string.profile)
                )
            },
            label = { Text(stringResource(R.string.profile)) },
        )
    }
}