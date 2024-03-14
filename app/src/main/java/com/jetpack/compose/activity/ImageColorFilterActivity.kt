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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jetpack.compose.R
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.base.AppPage
import com.jetpack.compose.data.HeaderData

/**
 * ImageColorMatrixActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class ImageColorFilterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPage(title = "色调调节") {
                ImageColorFilterComposable()
            }
        }
    }
}

@Composable
fun ImageColorFilterComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "使用 ColorFilter.tint(color, blendMode) 会将具有指定颜色的混合模式应用于 Image 可组合项。ColorFilter.tint(color, blendMode) 使用 BlendMode.SrcIn 对内容进行色调调节，这意味着系统将在屏幕上显示图片的位置显示所提供的颜色。这对于需要以不同方式设置主题的图标和矢量非常有用。"
            )
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(id = R.mipmap.banner),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.75f)
                    .clip(RoundedCornerShape(5.dp)),
                colorFilter = ColorFilter.tint(Color.Green, blendMode = BlendMode.Darken)
            )
        }
    }
}