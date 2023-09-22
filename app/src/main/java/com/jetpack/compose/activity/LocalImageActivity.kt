package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jetpack.compose.R
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.data.HeaderData

/**
 * LocalImageActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class LocalImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "本地图片") {
                LocalImageComposable()
            }
        }
    }
}

@Composable
fun LocalImageComposable() {
    Column {
        BuildHeader(
            HeaderData(
                text = "从磁盘加载图片（例如 PNG、JPEG、WEBP）或矢量资源，请将 painterResource API 与图片引用搭配使用。您不必知道资源的类型，只需在 Image 或 paint 修饰符中使用 painterResource 即可。"
            )
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(id = R.mipmap.banner),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .aspectRatio(1.75f)
            )
        }
    }
}