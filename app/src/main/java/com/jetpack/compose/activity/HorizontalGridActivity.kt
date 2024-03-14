package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.BuildSection
import com.jetpack.compose.base.AppPage
import com.jetpack.compose.data.DataRepository
import com.jetpack.compose.data.HeaderData

/**
 * HorizontalGridActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class HorizontalGridActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPage(title = "横向Grid") {
                BuildPictureHorizontalGrid()
            }
        }
    }
}

@Composable
private fun BuildPictureHorizontalGrid() {
    Column {
        BuildSection(
            HeaderData(
                "LazyVerticalGrid 和 LazyHorizontalGrid 可组合项为在网格中显示列表项提供支持。延迟垂直网格会在可垂直滚动容器中跨多个列显示其列表项，而延迟水平网格则会在水平轴上有相同的行为。"
            )
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            modifier = Modifier.height(200.dp),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(DataRepository.providePictures()) { item ->
                Image(
                    painter = painterResource(id = item), contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp))
                        .aspectRatio(1.75f)
                )
            }
        }
    }
}