package ua.edu.znu.geoquizcomposeedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.edu.znu.geoquizcomposeedu.ui.screens.ComponentScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.MainScreen
import ua.edu.znu.geoquizcomposeedu.ui.screens.MultiComponentScreen
import ua.edu.znu.geoquizcomposeedu.ui.theme.GeoQuizComposeEduTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val innerPadding = 12.dp
        setContent {
            GeoQuizComposeEduTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MainScreen(innerPadding)
//                    ComponentScreen(innerPadding)
                    MultiComponentScreen(innerPadding)
                }
            }

        }
    }
}
