package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.data.HeaderData

/**
 * ConstraintBarrierActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class ConstraintBarrierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "Constraint屏障") {
                ConstraintBarrierComposable()
            }
        }
    }
}

@Composable
fun ConstraintBarrierComposable() {
    Column {
        BuildHeader(
            HeaderData(
                text = "屏障线会引用多个可组合项，从而根据所指定边中处于最边缘位置的 widget 创建虚拟引导线。\n若要创建屏障线，请使用 createTopBarrier()（或 createBottomBarrier()、createEndBarrier()、createStartBarrier()），并提供构成屏障线的引用。"
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
                    .padding(10.dp)
            ) {
                val (btnLeft, btnCenter, item1, item2) = createRefs()
                val endBarrier = createEndBarrier(btnLeft, btnCenter)

                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnLeft) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }) {
                    Text(text = "Left")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btnCenter) {
                        centerTo(parent)
                    }) {
                    Text(text = "Right")
                }
                Text(text = "Barrier Item1",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .constrainAs(item1) {
                            start.linkTo(endBarrier)
                        })
                Text(text = "Barrier Item2",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .constrainAs(item2) {
                            start.linkTo(item1.start)
                            top.linkTo(item1.bottom)
                        }
                )
            }
        }
    }
}
