package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.data.HeaderData

/**
 * BroadcastReceiverActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class BroadcastReceiverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "Jetpack Compose") {
                BroadcastComposable()
            }
        }
    }
}

@Preview
@Composable
fun BroadcastComposable() {
    Column {
        BuildHeader(
            HeaderData(
                text = "利用 LocalContext 来使用当前上下文以及 rememberUpdatedState 和 DisposableEffect 附带效应"
            )
        )
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
}