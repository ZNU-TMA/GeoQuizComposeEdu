package ua.edu.znu.geoquizcomposeedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ua.edu.znu.geoquizcomposeedu.educational.UserListExample
import ua.edu.znu.geoquizcomposeedu.ui.screens.QuestionListScreen
import ua.edu.znu.geoquizcomposeedu.ui.theme.GeoQuizComposeEduTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizComposeEduTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MainScreen(
//                        innerPadding = innerPadding
//                    )
//                    ComponentScreen(innerPadding)
//                    MultiComponentScreen(innerPadding)
//                    SimpleListExample(innerPadding)
//                    UserListExample(innerPadding)
                    QuestionListScreen(innerPadding)
                }
            }
        }
    }
}
