package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.AppPage
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.data.HeaderData

/**
 * ContentSlotActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class ContentSlotActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPage(title = "内容槽") {
                ButtonComposable()
            }
        }
    }
}

@Composable
fun ButtonComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "支持内部内容（文本标签、图标等）的 Material 组件往往会提供“槽”（即接受可组合内容的通用 lambda），而且还会提供尺寸和内边距等公共常量，从而支持设置内部内容的布局，使之符合 Material 规范。例如：Button"
            )
        )
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
}