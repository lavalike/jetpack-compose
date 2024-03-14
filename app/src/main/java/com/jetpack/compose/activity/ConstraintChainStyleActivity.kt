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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.jetpack.compose.base.AppPage
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.data.HeaderData

/**
 * ConstraintChainStyleActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class ConstraintChainStyleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPage(title = "Constraint Chain Style") {
                ConstraintChainStyleComposable()
            }
        }
    }
}

@Composable
fun ConstraintChainStyleComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "链在单条轴（水平或垂直方向）上提供类似于组的行为。另一条轴可单独约束。\n链可通过不同的 ChainStyles（决定了可组合项周围空间的处理方式）配置，例如：\nChainStyle.Spread：空间会在所有可组合项之间均匀分布，包括第一个可组合项之前和最后一个可组合项之后的可用空间\nChainStyle.SpreadInside：空间会在所有可组合项之间均匀分布，不包括第一个可组合项之前或最后一个可组合项之后的任何可用空间\nChainStyle.Packed：空间会分布在第一个可组合项之前和最后一个可组合项之后，各个可组合项之间没有空间，会挤在一起"
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
                val (btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9) = createRefs()
                createHorizontalChain(btn1, btn2, btn3, chainStyle = ChainStyle.Packed)
                createHorizontalChain(btn4, btn5, btn6, chainStyle = ChainStyle.Spread)
                createHorizontalChain(btn7, btn8, btn9, chainStyle = ChainStyle.SpreadInside)

                // ChainStyle.Packed
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(btn2.start)
                    }) {
                    Text(text = "button1")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn2) {
                        top.linkTo(parent.top)
                        start.linkTo(btn1.end)
                        end.linkTo(btn3.start)
                    }) {
                    Text(text = "button2")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn3) {
                        top.linkTo(parent.top)
                        start.linkTo(btn2.end)
                        end.linkTo(parent.end)
                    }) {
                    Text(text = "button3")
                }

                // ChainStyle.Spread
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn4) {
                        top.linkTo(btn1.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(btn5.start)
                    }) {
                    Text(text = "button4")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn5) {
                        top.linkTo(btn4.top)
                        start.linkTo(btn4.end)
                        end.linkTo(btn6.start)
                    }) {
                    Text(text = "button5")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn6) {
                        top.linkTo(btn4.top)
                        start.linkTo(btn5.end)
                        end.linkTo(parent.end)
                    }) {
                    Text(text = "button6")
                }
                // ChainStyle.SpreadInside
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn7) {
                        top.linkTo(btn4.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(btn8.start)
                    }) {
                    Text(text = "button4")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn8) {
                        top.linkTo(btn7.top)
                        start.linkTo(btn7.end)
                        end.linkTo(btn9.start)
                    }) {
                    Text(text = "button5")
                }
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(btn9) {
                        top.linkTo(btn7.top)
                        start.linkTo(btn8.end)
                        end.linkTo(parent.end)
                    }) {
                    Text(text = "button6")
                }
            }
        }
    }
}