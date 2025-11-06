package ua.edu.znu.geoquizcomposeedu.educational.lazycolumns

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.javafaker.Faker
import ua.edu.znu.geoquizcomposeedu.R
import java.util.Random

data class User(
    val id: Long,
    val photoUrl: String,
    val name: String,
    val status: String
)

fun createUserList(): List<User> {
    val faker: Faker = Faker.instance(Random(0))
    val images: MutableList<String> = mutableListOf(
        "https://images.unsplash.com/photo-1506744038136-46273834b3fb",
        "https://images.unsplash.com/photo-1465101046530-73398c7f28ca",
        "https://images.unsplash.com/photo-1519125323398-675f0ddb6308",
        "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e",
        "https://images.unsplash.com/photo-1508672019048-805c876b67e2",
        "https://images.unsplash.com/photo-1519985176271-adb1088fa94c",
        "https://images.unsplash.com/photo-1438761681033-6461ffad8d80",
        "https://images.unsplash.com/photo-1470770841072-f978cf4d019e",
        "https://images.unsplash.com/photo-1534528741775-53994a69daeb",
        "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df"
    )
    val list: List<User> = List(100) { index ->
        User(
            id = index + 1L,
            photoUrl = images[index % images.size] + "?w=200&h=200&fit=crop",
            name = faker.name().fullName(),
            status = faker.shakespeare().hamletQuote()
        )
    }
    return list
}

@Composable
fun UserListExample(
    innerPadding: PaddingValues,
) {
    var userlist: List<User> by remember {
        mutableStateOf(createUserList())
    }
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        items(
            items = userlist,
            key = { user -> user.id }
        ) { user ->
            UserCard(
                user = user,
                onUserClicked = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.user_clicked, user.name),
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onUserDeleted = {
                    userlist -= user
                },
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun UserCard(
    user: User,
    modifier: Modifier = Modifier,
    onUserClicked: () -> Unit = {},
    onUserDeleted: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
            ) {
                onUserClicked()
            }
    ) {
        Row(Modifier.padding(8.dp)) {
            UserImage(user.photoUrl)
            Spacer(Modifier.width(16.dp))
            UserInfo(user)
            DeletedUserButton(onUserDeleted = onUserDeleted)
        }
    }
}

@Composable
fun UserImage(photoUrl: String) {
    val placeholder: VectorPainter = rememberVectorPainter(
        image = Icons.Rounded.AccountCircle
    )
    AsyncImage(
        model = photoUrl,
        placeholder = placeholder,
        contentDescription = stringResource(R.string.user_photo),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .aspectRatio(1f / 1f)
            .clip(CircleShape)
    )
}

@Composable
private fun RowScope.UserInfo(user: User) {
    Column(
        modifier = Modifier
            .weight(1f)
    ) {
        Text(
            text = user.name,
            maxLines = 1,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = user.status,
            maxLines = 2,
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun RowScope.DeletedUserButton(
    onUserDeleted: () -> Unit
) {
    IconButton(
        onClick = onUserDeleted,
        modifier = Modifier
            .align(alignment = Alignment.CenterVertically)
    ) {
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = stringResource(R.string.delete_user),
        )
    }
}