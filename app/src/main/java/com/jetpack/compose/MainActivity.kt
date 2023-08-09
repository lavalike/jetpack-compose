package com.jetpack.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.ui.theme.JetpackcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn {
                        item { BuildTitle("控件") }
                        DataRepository.provideMessageCards().forEach { message ->
                            item {
                                BuildMessageCard(message = message)
                            }
                        }
                        item { BuildTitle("HorizontalGrid") }
                        item { BuildPictureHorizontalGrid() }
                        item { BuildTitle("VerticalGrid") }
                        item { BuildPictureVerticalGrid() }
                    }
                }
            }
        }
    }
}

@Composable
private fun BuildPictureHorizontalGrid() {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        modifier = Modifier.height(100.dp),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(DataRepository.providePictures()) { item ->
            Image(
                painter = painterResource(id = item), contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(5.dp))
            )
        }
    }
}

@Composable
private fun BuildPictureVerticalGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(245.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(DataRepository.providePictures()) { item ->
            Image(
                painter = painterResource(id = item), contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(5.dp))
            )
        }
    }
}

@Composable
private fun BuildMessageCard(message: Message) {
    MessageCard(
        Message(
            message.author,
            message.body,
        )
    )
}

@Composable
fun BuildTitle(title: String) {
    Text(
        title,
        modifier = Modifier.padding(10.dp),
        color = Color.Black,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun MessageCard(data: Message) {
    Row(modifier = Modifier.padding(all = 10.dp)) {
        Image(
            painter = painterResource(id = R.mipmap.banner),
            contentDescription = stringResource(
                id = R.string.app_name
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size = 80.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp, MaterialTheme.colorScheme.primary,
                    CircleShape,
                )
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = data.author, color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))

            var expand by remember { mutableStateOf(false) }

            Surface(
                shape = MaterialTheme.shapes.medium,
                color = if (expand) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shadowElevation = 1.dp,
                tonalElevation = 1.dp,
                modifier = Modifier.animateContentSize(),
            ) {
                Text(
                    text = data.body,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            expand = !expand
                        },
                    maxLines = if (expand) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis,
                    color = if (expand) Color.White else MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}


@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Night Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MessageCardPreview() {
    JetpackcomposeTheme {
        Surface {
            MessageCard(
                Message(
                    "Colleague",
                    "Hey, take a look at Jetpack Compose, it's great! We toggle the expand variable when we click on this Column"
                )
            )
        }
    }
}