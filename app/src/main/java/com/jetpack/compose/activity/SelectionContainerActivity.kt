package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.data.HeaderData

/**
 * SelectionContainerActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class SelectionContainerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "文本选择") {
                SelectionTextComposable()
            }
        }
    }
}


@Composable
fun SelectionTextComposable() {
    Column {
        BuildHeader(
            HeaderData(
                text = "默认情况下，可组合项不可选择，这意味着在默认情况下用户无法从您的应用中选择和复制文字。要启用文字选择，需要使用 SelectionContainer 可组合项封装文字元素。\n您可能想为可选择区域的特定部分停用选择功能。如果要执行此操作，您需要使用 DisableSelection 可组合项来封装不可选择的部分。"
            )
        )
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(5.dp)),
            shadowElevation = 1.dp,
            tonalElevation = 1.dp,
        ) {
            SelectionContainer {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("This text is selectable")
                    Text("This one too")
                    Text("This one as well")
                    DisableSelection {
                        Text("But not this one")
                        Text("Neither this one")
                    }
                    Text("But again, you can select this one")
                    Text("And this one too")
                }
            }
        }
    }
}