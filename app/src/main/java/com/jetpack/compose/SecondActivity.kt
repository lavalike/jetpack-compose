package com.jetpack.compose

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.compose.databinding.ActivitySecondBinding
import com.jetpack.compose.ui.theme.JetpackComposeTheme

/**
 * SecondActivity
 *
 * @author : zhen51.wang
 * @date : 2023/8/10/010
 */
class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            btnNative1.setOnClickListener { }
            btnNative2.setOnClickListener { }
            root.addView(ComposeView(root.context).apply {
                setContent {
                    JetpackComposeTheme {
                        Column {
                            NativeComposable()
                        }
                    }
                }
            }, 1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NativeComposable() {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = "将 Compose 与现有界面集成",
                modifier = Modifier.padding(10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Jetpack Compose 旨在配合既有的基于视图的界面构造方式一起使用。如果您要构建新应用，最好的选择可能是使用 Compose 实现整个界面。但是，如果您要修改现有应用，那么请不要一次性迁移整个应用，而是可以将 Compose 与现有的界面设计实现相结合。",
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                color = MaterialTheme.colorScheme.secondary,
            )
            val context = LocalContext.current
            Button(
                onClick = {
                    Toast.makeText(context, "返回上一页", Toast.LENGTH_SHORT).show()
                    (context as? Activity)?.finish()
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text("返回上一页")
            }
        }
    }
}