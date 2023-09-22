package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.data.HeaderData

/**
 * GestureDetectActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class GestureDetectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "手势检测") {
                GestureDetectComposable()
            }
        }
    }
}

@Composable
fun GestureDetectComposable() {
    Column {
        BuildHeader(
            HeaderData(
                text = "Compose 提供了多种 API，可帮助您检测用户互动生成的手势。" +
                        "\n其中一些级别较高，旨在覆盖最常用的手势。例如，clickable 修饰符可用于轻松检测点击，此外它还提供无障碍功能，并在点按时显示视觉指示（例如涟漪）。" +
                        "\n还有一些不太常用的手势检测器，它们在较低级别提供更大的灵活性，例如 PointerInputScope.detectTapGestures 或 PointerInputScope.detectDragGestures，但不提供额外功能。"
            )
        )
        var tapText by remember {
            mutableStateOf("Detect Tap Gestures")
        }
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(5.dp))
                .padding(10.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            tapText = "You Pressed."
                        },
                        onDoubleTap = {
                            tapText = "You Double Tapped."
                        },
                        onLongPress = {
                            tapText = "You Long Pressed."
                        },
                        onTap = {
                            tapText = "You Tapped."
                        },
                    )
                },
            shadowElevation = 1.dp,
            tonalElevation = 1.dp,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(tapText)
            }
        }

        var dragText by remember {
            mutableStateOf("Detect Drag Gestures")
        }
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(5.dp))
                .padding(10.dp)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            dragText = "PointerType: ${change.type}\nDragAmount: $dragAmount"
                        },
                        onDragStart = {
                            dragText = "On Drag Start"
                        },
                        onDragCancel = {
                            dragText = "On Drag Cancel"
                        },
                        onDragEnd = {
                            dragText = "On Drag End"
                        },
                    )
                },
            shadowElevation = 1.dp,
            tonalElevation = 1.dp,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(dragText)
            }
        }
    }
}