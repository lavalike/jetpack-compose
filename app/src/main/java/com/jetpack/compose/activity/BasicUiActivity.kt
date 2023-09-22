package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jetpack.compose.R
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.data.HeaderData

/**
 * BasicUiActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class BasicUiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "基本控件") {
                BuildMessageCard()
            }
        }
    }
}

@Composable
fun BuildMessageCard() {
    Column {
        BuildHeader(
            HeaderData(
                text = "Jetpack Compose 是用于构建原生 Android 界面的新工具包。它使用更少的代码、强大的工具和直观的 Kotlin API，可以帮助您简化并加快 Android 界面开发。"
            )
        )

        Row(modifier = Modifier.padding(all = 10.dp)) {
            val rainbowColorBrush = remember {
                Brush.sweepGradient(
                    listOf(
                        Color(0xFF9575CD),
                        Color(0xFFBA68C8),
                        Color(0xFFE57373),
                        Color(0xFFFFB74D),
                        Color(0xFFFFF176),
                        Color(0xFFAED581),
                        Color(0xFF4DD0E1),
                        Color(0xFF9575CD)
                    )
                )
            }
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
                        BorderStroke(3.dp, rainbowColorBrush), CircleShape,
                    )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Colleague", color = MaterialTheme.colorScheme.primary,
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
                        text = "Hey, take a look at Jetpack Compose, it's great! We toggle the expand variable when we click on this Column",
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
}