package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.base.AppPage
import com.jetpack.compose.data.HeaderData

/**
 * BasicTextActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class BasicTextActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPage(title = "基本文本") {
                BasicTextComposable()
            }
        }
    }
}

@Composable
fun BasicTextComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "文字对任何界面都属于核心内容，而利用 Jetpack Compose 可以更轻松地显示或写入文字。Compose 可以充分利用其构建块的组合，这意味着您无需覆盖各种属性和方法，也无需扩展大型类，即可拥有特定的可组合项设计以及按您期望的方式运行的逻辑。"
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
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Hello World", fontFamily = FontFamily.Serif)
                Text("Hello World", fontFamily = FontFamily.SansSerif)
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append("H")
                        }
                        append("ello ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Red
                            )
                        ) {
                            append("W")
                        }
                        append("orld")
                    }
                )
                Text(
                    text = "Hello World!",
                    style = TextStyle(
                        fontSize = 24.sp,
                        shadow = Shadow(
                            color = Color.Blue,
                            offset = Offset(5f, 10f),
                            blurRadius = 3f,
                        )
                    )
                )
            }
        }
    }
}