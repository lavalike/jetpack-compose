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
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.material3.Divider
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.jetpack.compose.data.DataRepository
import com.jetpack.compose.data.Message
import com.jetpack.compose.data.TitleData
import com.jetpack.compose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
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
                                        title = "ConstraintLayout",
                                        description = "ConstraintLayout 是一种布局，让您可以相对于屏幕上的其他可组合项来放置可组合项。它是一种实用的替代方案，可代替使用多个已嵌套的 Row、Column、Box 和其他自定义布局元素这种做法。在实现对齐要求比较复杂的较大布局时，ConstraintLayout 很有用。\n在 View 系统中，建议使用 ConstraintLayout 来创建复杂的大型布局，因为扁平视图层次结构比嵌套视图的效果更好。不过，这在 Compose 中不是什么问题，因为 Compose 能够高效地处理较深的布局层次结构。"
                                    )
                                )
                            }
                            item {
                                ConstraintLayoutComposable()
                            }
                            item {
                                BuildTitle(
                                    TitleData(
                                        title = "固有特性的实际运用",
                                        description = "Compose 有一项规则，即，子项只能测量一次，测量两次就会引发运行时异常。但是，有时需要先收集一些关于子项的信息，然后再测量子项。\n借助固有特性，您可以先查询子项，然后再进行实际测量。\n假设我们需要创建一个可组合项，该可组合项在屏幕上显示两个用分隔线隔开的文本。我们该怎么做？我们可以将两个 Text 放在同一 Row，并在其中最大程度地扩展，另外在中间放置一个 Divider。我们需要将分隔线的高度设置为与最高的 Text 相同，粗细设置为 width = 1.dp。预览时，我们发现分隔线扩展到整个屏幕，这并不是我们想要的效果。之所以出现这种情况，是因为 Row 会逐个测量每个子项，并且 Text 的高度不能用于限制 Divider。我们希望 Divider 以一个给定的高度来填充可用空间。为此，我们可以使用 height(IntrinsicSize.Min) 修饰符，将其子项的高度强行调整为最小固有高度。由于该修饰符具有递归性，因此它将查询 Row 及其子项 minIntrinsicHeight。"
                                    )
                                )
                            }
                            item {
                                IntrinsicHeightComposable()
                            }
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
fun ConstraintLayoutComposable() {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(10.dp),
        shadowElevation = 1.dp,
        tonalElevation = 1.dp,
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp)
        ) {
            val (btnLeft, btnRight, btnTop, btnBottom, btnCenter) = createRefs()

            Button(onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(btnLeft) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }) {
                Text(text = "Left")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(btnRight) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }) {
                Text(text = "Right")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(btnTop) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(text = "Top")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(btnBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(text = "Bottom")
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.constrainAs(btnCenter) {
                    centerTo(parent)
                }) {
                Text(text = "Bottom")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutComposablePreview() {
    ConstraintLayoutComposable()
}

@Composable
fun IntrinsicHeightComposable() {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(10.dp),
        shadowElevation = 1.dp,
        tonalElevation = 1.dp,
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Text(
                text = "Hi",
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .wrapContentWidth(Alignment.Start)
            )
            Divider(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Text(
                text = "There",
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .wrapContentWidth(Alignment.End)
            )
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
        modifier = Modifier.height(300.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(DataRepository.providePictures()) { item ->
            Image(
                painter = painterResource(id = item), contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .aspectRatio(1.5f),
                contentScale = ContentScale.Crop,
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
    JetpackComposeTheme {
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