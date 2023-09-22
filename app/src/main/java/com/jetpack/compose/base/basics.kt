package com.jetpack.compose.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.compose.data.HeaderData
import com.jetpack.compose.ui.theme.JetpackComposeTheme

/**
 * basics
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildPage(title: String, content: @Composable () -> Unit) {
    JetpackComposeTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = title, color = Color.White) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                )
            },
        ) { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                content()
            }
        }
    }
}

@Composable
fun BuildHeader(data: HeaderData) {
    Column(modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)) {
        data.text?.let {
            Text(
                text = it,
                color = Color.Black,
                fontSize = 16.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BuildHeaderPreview() {
    BuildHeader(
        data = HeaderData(
            text = "如需从互联网加载图片，有几个第三方库可协助您处理该流程。图片加载库可以为您完成许多繁重工作；而且可以同时处理缓存（这样您就不必多次下载图片）和网络逻辑，从而下载图片并在屏幕上进行显示。"
        )
    )
}