package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.data.HeaderData

/**
 * TextFieldActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "文本输入") {
                TextFieldComposable()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComposable() {
    Column {
        BuildSection(
            HeaderData(
                text = "TextField 允许用户输入和修改文字。TextField 实现分为两个级别：\n" +
                        "TextField 是 Material Design 实现。\n" +
                        "BasicTextField 允许用户通过硬件或软件键盘编辑文本，但没有提供提示或占位符等装饰。"
            )
        )
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            var text by remember { mutableStateOf("Hello") }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = { Text("Material Design") },
            )

            var outlinedText by remember { mutableStateOf("Hello") }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = outlinedText,
                onValueChange = { outlinedText = it },
                label = { Text("轮廓样式") },
                maxLines = 2,
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                )
            )

            var password by rememberSaveable { mutableStateOf("123456") }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
        }
    }
}