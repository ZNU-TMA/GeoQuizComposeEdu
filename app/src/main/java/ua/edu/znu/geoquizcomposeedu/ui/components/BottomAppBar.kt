package ua.edu.znu.geoquizcomposeedu.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ua.edu.znu.geoquizcomposeedu.R
import ua.edu.znu.geoquizcomposeedu.nav.Routes

@Composable
fun BottomAppBar(
    navController: NavHostController
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(Routes.Home)
            },
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
            onClick = { navController.navigate(Routes.QuestionList) },
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