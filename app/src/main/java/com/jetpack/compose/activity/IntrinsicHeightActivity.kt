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
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.AppPage
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.data.HeaderData

/**
 * IntrinsicHeightActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class IntrinsicHeightActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPage(title = "固有特性") {
                IntrinsicHeightComposable()
            }
        }
    }
}

@Composable
fun IntrinsicHeightComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "Compose 有一项规则，即，子项只能测量一次，测量两次就会引发运行时异常。但是，有时需要先收集一些关于子项的信息，然后再测量子项。\n借助固有特性，您可以先查询子项，然后再进行实际测量。\n假设我们需要创建一个可组合项，该可组合项在屏幕上显示两个用分隔线隔开的文本。我们该怎么做？我们可以将两个 Text 放在同一 Row，并在其中最大程度地扩展，另外在中间放置一个 Divider。我们需要将分隔线的高度设置为与最高的 Text 相同，粗细设置为 width = 1.dp。预览时，我们发现分隔线扩展到整个屏幕，这并不是我们想要的效果。之所以出现这种情况，是因为 Row 会逐个测量每个子项，并且 Text 的高度不能用于限制 Divider。我们希望 Divider 以一个给定的高度来填充可用空间。为此，我们可以使用 height(IntrinsicSize.Min) 修饰符，将其子项的高度强行调整为最小固有高度。由于该修饰符具有递归性，因此它将查询 Row 及其子项 minIntrinsicHeight。"
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