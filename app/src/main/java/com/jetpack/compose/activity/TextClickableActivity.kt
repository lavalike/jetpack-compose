package com.jetpack.compose.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.data.HeaderData

/**
 * TextClickableActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class TextClickableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "文本点击") {
                ClickableComposable()
            }
        }
    }
}

@Composable
fun ClickableComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "如需监听 Text 的点击次数，您可以添加 clickable 修饰符。不过，如果您想在 Text 可组合项内获取点击位置，在对文字的不同部分执行不同操作的情况下，您需要改用 ClickableText。\n当用户点击 Text 可组合项时，您可能想向 Text 值的某一部分附加额外信息，例如向特定字词附加可在浏览器中打开的网址。如果要执行此操作，您需要附加一个注解，用于获取一个标记 (String)、一个项 (String) 和一个文字范围作为参数。在 AnnotatedString 中，这些注解可以按照其标记或文字范围进行过滤。"
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
            val context = LocalContext.current
            Column(modifier = Modifier.padding(10.dp)) {
                ClickableText(
                    text = AnnotatedString("Click Me"),
                    onClick = { offset ->
                        Toast.makeText(
                            context,
                            "$offset -th character is clicked.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )

                val annotatedString = buildAnnotatedString {
                    append("Click ")

                    pushStringAnnotation(
                        "URL",
                        "https://developer.android.google.cn/jetpack/compose/text?hl=zh-cn"
                    )

                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("developer.android.google.cn/jetpack/compose")
                    }

                    pop()
                }

                ClickableText(text = annotatedString, onClick = { offset ->
                    annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                        .firstOrNull()?.let { annotation ->
                            Toast.makeText(
                                context,
                                annotation.item,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                })
            }
        }
    }
}