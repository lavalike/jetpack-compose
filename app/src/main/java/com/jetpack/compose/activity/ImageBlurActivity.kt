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
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jetpack.compose.R
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.base.AppPage
import com.jetpack.compose.data.HeaderData

/**
 * ImageBlurActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class ImageBlurActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPage(title = "图片模糊") {
                ImageBlurComposable()
            }
        }
    }
}

@Composable
fun ImageBlurComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "如需对图片进行模糊处理，请使用 Modifier.blur() 并同时提供 radiusX 和 radiusY，两者分别指定水平方向和垂直方向的模糊半径。"
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
                    .blur(
                        radiusX = 20.dp,
                        radiusY = 20.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(5.dp))
                    ),
            )
        }
    }
}