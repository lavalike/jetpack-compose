package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.data.HeaderData

/**
 * ConstraintLayoutActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "ConstraintLayout") {
                ConstraintLayoutComposable()
            }
        }
    }
}

@Composable
fun ConstraintLayoutComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "ConstraintLayout 是一种布局，让您可以相对于屏幕上的其他可组合项来放置可组合项。它是一种实用的替代方案，可代替使用多个已嵌套的 Row、Column、Box 和其他自定义布局元素这种做法。在实现对齐要求比较复杂的较大布局时，ConstraintLayout 很有用。\n在 View 系统中，建议使用 ConstraintLayout 来创建复杂的大型布局，因为扁平视图层次结构比嵌套视图的效果更好。不过，这在 Compose 中不是什么问题，因为 Compose 能够高效地处理较深的布局层次结构。"
            )
        )
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .padding(10.dp),
            shadowElevation = 1.dp,
            tonalElevation = 1.dp,
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp)
            ) {
                val (btnLeft, btnRight, btnTop, btnBottom, btnCenter) = createRefs()

                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnLeft) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }) {
                    Text(text = "Left")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnRight) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }) {
                    Text(text = "Right")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnTop) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Text(text = "Top")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnBottom) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    Text(text = "Bottom")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnCenter) {
                        centerTo(parent)
                    }) {
                    Text(text = "Bottom")
                }
            }
        }
    }
}