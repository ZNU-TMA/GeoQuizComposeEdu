package ua.edu.znu.geoquizcomposeedu.educational.navigation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.edu.znu.geoquizcomposeedu.educational.navigation.data.Subject
import ua.edu.znu.geoquizcomposeedu.educational.navigation.data.SubjectRepository

@Composable
fun FirstScreen(
    onListItemClick: (Subject) -> Unit
) {
     LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = SubjectRepository.subjects,
            key = { subject -> subject.id }
        ) { subject ->
            Text(
                text = "${subject.text} - ${subject.value}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        onListItemClick(subject)
                    },
            )
        }
    }
}
