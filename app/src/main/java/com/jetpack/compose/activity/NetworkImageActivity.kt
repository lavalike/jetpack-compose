package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jetpack.compose.R
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.data.HeaderData

/**
 * NetworkImageActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class NetworkImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "网络图片") {
                NetworkImageComposable()
            }
        }
    }
}

@Composable
fun NetworkImageComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "如需从互联网加载图片，有几个第三方库可协助您处理该流程。图片加载库可以为您完成许多繁重工作；而且可以同时处理缓存（这样您就不必多次下载图片）和网络逻辑，从而下载图片并在屏幕上进行显示。"
            )
        )
        Column(modifier = Modifier.padding(10.dp)) {
            AsyncImage(
                model = "https://t7.baidu.com/it/u=3713375227,571533122&fm=193&f=GIF",
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = Color.Gray)
                    .aspectRatio(1.75f)
            )
        }
    }
}