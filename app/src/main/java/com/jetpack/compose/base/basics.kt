package com.jetpack.compose.base

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun AppPage(title: String, content: @Composable () -> Unit) {
    JetpackComposeTheme {
        Scaffold(
            topBar = {
                AppBar(title = title)
            },
        ) { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                content()
            }
        }
    }
}

@Composable
fun AppBar(title: String) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = MaterialTheme.colorScheme.primary),
    ) {
        Box(
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp, end = 5.dp, bottom = 5.dp)
                .clip(CircleShape)
                .fillMaxHeight()
                .aspectRatio(1f)
                .clickable(
                    interactionSource = null,
                    indication = ripple(bounded = false)
                ) {
                    if (context is Activity) {
                        context.finish()
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White,
            )
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            fontSize = 20.sp,
            color = Color.White,
        )
    }
}

@Composable
fun BuildSection(data: HeaderData) {
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
fun BuildSectionPreview() {
    BuildSection(
        data = HeaderData(
            text = "如需从互联网加载图片，有几个第三方库可协助您处理该流程。图片加载库可以为您完成许多繁重工作；而且可以同时处理缓存（这样您就不必多次下载图片）和网络逻辑，从而下载图片并在屏幕上进行显示。"
        )
    )
}