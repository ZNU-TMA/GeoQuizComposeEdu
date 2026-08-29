package ua.edu.znu.geoquizcomposeedu.educational.multicomponents.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Container(
    name: String,
    content: @Composable () -> Unit
) {
    Card(
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
        )
        {
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        content()
    }
}
