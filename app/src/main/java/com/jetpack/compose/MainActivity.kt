package com.jetpack.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jetpack.compose.data.DataRepository
import com.jetpack.compose.data.IndexEntity
import com.jetpack.compose.ui.theme.JetpackComposeTheme

/**
 * MainActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                @OptIn(ExperimentalMaterial3Api::class)
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Jetpack Compose", color = Color.White) },
                            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Send, contentDescription = "Send")
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End,
                ) { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                            items(DataRepository.index()) {
                                BuildItem(data = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BuildItem(data: IndexEntity) {
    val context = LocalContext.current
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.5f)
            .clip(RoundedCornerShape(5.dp))
            .padding(10.dp),
        shadowElevation = 1.dp,
        tonalElevation = 1.dp,
    ) {
        Box(
            modifier = Modifier.clickable {
                context.startActivity(Intent(context, data.clazz))
            },
            contentAlignment = Alignment.Center
        ) {
            Text(text = data.title)
        }
    }
}

