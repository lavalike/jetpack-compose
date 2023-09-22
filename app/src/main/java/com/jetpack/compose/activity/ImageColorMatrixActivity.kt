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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jetpack.compose.R
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.data.HeaderData

/**
 * ImageColorMatrixActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class ImageColorMatrixActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "图片滤镜") {
                ImageColorMatrixComposable()
            }
        }
    }
}

@Composable
fun ImageColorMatrixComposable() {
    Column {
        BuildHeader(
            HeaderData(
                text = "颜色矩阵 ColorFilter 选项可用于转换图片。例如，如需对图片应用黑白滤镜，您可以使用 ColorMatrix 并将饱和度设置为 0f。"
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
                colorFilter = ColorFilter.colorMatrix(colorMatrix = ColorMatrix().apply {
                    setToSaturation(0f)
                })
            )
        }
    }
}