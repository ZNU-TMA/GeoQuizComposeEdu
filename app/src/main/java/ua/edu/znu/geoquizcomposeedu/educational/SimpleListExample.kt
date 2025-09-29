package ua.edu.znu.geoquizcomposeedu.educational

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleListExample(innerPadding: PaddingValues) {

    val list1: List<String> = remember {
        List(100) { index -> "Item #${index + 1}" }
    }

    val list2: List<String> = remember {
        List(50) { index -> "Another Item #${index + 1}" }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        listHeader("Header 1")
        listItems(list1)
        listHeader("Header 2")
        listItems(list2)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.listHeader(text: String) {
    stickyHeader {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        )
    }
}

fun LazyListScope.listItems(list: List<String>) {
    items(list) { item ->
        Text(
            text = item,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleListExamplePreview() {
    val innerPadding = PaddingValues(16.dp)
    SimpleListExample(innerPadding = innerPadding)
}
