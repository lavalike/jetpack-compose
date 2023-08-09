package com.jetpack.compose

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.data.DataRepository
import com.jetpack.compose.data.Message
import com.jetpack.compose.data.TitleData
import com.jetpack.compose.ui.theme.JetpackcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposeTheme {
                @OptIn(ExperimentalMaterial3Api::class)
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Jetpack Compose", color = Color.White) },
                            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Send, contentDescription = "Send")
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End,
                ) { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        val listState = rememberLazyListState()
                        LazyColumn(state = listState) {
                            item {
                                BuildTitle(
                                    TitleData(
                                        title = "内容槽",
                                        description = "支持内部内容（文本标签、图标等）的 Material 组件往往会提供“槽”（即接受可组合内容的通用 lambda），而且还会提供尺寸和内边距等公共常量，从而支持设置内部内容的布局，使之符合 Material 规范。例如：Button"
                                    )
                                )
                            }
                            item {
                                ButtonComposable()
                            }
                            item {
                                BuildTitle(
                                    TitleData(
                                        title = "控件",
                                        description = "Jetpack Compose 是用于构建原生 Android 界面的新工具包。它使用更少的代码、强大的工具和直观的 Kotlin API，可以帮助您简化并加快 Android 界面开发。"
                                    )
                                )
                            }
                            DataRepository.provideMessageCards().forEach { message ->
                                item {
                                    BuildMessageCard(message = message)
                                }
                            }
                            item {
                                BuildTitle(
                                    TitleData(
                                        "约束布局",
                                        "在设计布局时，应考虑不同的屏幕方向和设备类型尺寸。Compose 提供了一些开箱即用的机制，可帮助您根据各种屏幕配置调整可组合项的布局。\n如需了解来自父项的约束条件并相应地设计布局，您可以使用 BoxWithConstraints。您可以在内容 lambda 的作用域内找到测量约束条件。您可以使用这些测量约束条件，为不同的屏幕配置组成不同的布局：",
                                    )
                                )
                            }
                            item { WithConstraintsComposable() }
                            item {
                                BuildTitle(
                                    TitleData(
                                        "HorizontalGrid",
                                        "LazyVerticalGrid 和 LazyHorizontalGrid 可组合项为在网格中显示列表项提供支持。延迟垂直网格会在可垂直滚动容器中跨多个列显示其列表项，而延迟水平网格则会在水平轴上有相同的行为。"
                                    )
                                )
                            }
                            item { BuildPictureHorizontalGrid() }
                            item {
                                BuildTitle(
                                    TitleData(
                                        "VerticalGrid",
                                        "LazyVerticalGrid 和 LazyHorizontalGrid 可组合项为在网格中显示列表项提供支持。延迟垂直网格会在可垂直滚动容器中跨多个列显示其列表项，而延迟水平网格则会在水平轴上有相同的行为。"
                                    )
                                )
                            }
                            item { BuildPictureVerticalGrid() }
                        }

                        val showButton by remember {
                            derivedStateOf {
                                listState.firstVisibleItemIndex > 0
                            }
                        }
                        AnimatedVisibility(
                            visible = showButton,
                            modifier = Modifier.align(Alignment.BottomStart),
                        ) {
                            Button(
                                onClick = {
                                    Toast.makeText(
                                        applicationContext,
                                        "you clicked animated button",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }, modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Text(text = "Animated Button")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonComposable() {
    Button(
        onClick = { /* ... */ },
        // Uses ButtonDefaults.ContentPadding by default
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        ),
        modifier = Modifier.padding(10.dp)
    ) {
        // Inner content including an icon and a text label
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Like")
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonComposablePreview() {
    ButtonComposable()
}

@Composable
fun WithConstraintsComposable() {
    BoxWithConstraints {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 1.dp,
            tonalElevation = 1.dp,
            modifier = Modifier
                .animateContentSize()
                .padding(10.dp),
        ) {
            Text(
                "My minHeight is $minHeight while my maxWidth is $maxWidth",
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WithConstraintsComposablePreview() {
    Column {
        BuildTitle(
            data = TitleData(
                "约束布局",
                "在设计布局时，应考虑不同的屏幕方向和设备类型尺寸。Compose 提供了一些开箱即用的机制，可帮助您根据各种屏幕配置调整可组合项的布局。",
            )
        )
        WithConstraintsComposable()
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
fun BuildTitle(data: TitleData) {
    Column(modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)) {
        Text(
            data.title,
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            data.description,
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall
        )
    }
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