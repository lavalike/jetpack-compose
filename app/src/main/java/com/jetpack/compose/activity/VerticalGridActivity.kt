package com.jetpack.compose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.data.DataRepository
import com.jetpack.compose.data.HeaderData

/**
 * HorizontalGridActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class VerticalGridActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "纵向Grid") {
                BuildPictureVerticalGrid()
            }
        }
    }
}

@Composable
private fun BuildPictureVerticalGrid() {
    Column {
        BuildHeader(
            HeaderData(
                "LazyVerticalGrid 和 LazyHorizontalGrid 可组合项为在网格中显示列表项提供支持。延迟垂直网格会在可垂直滚动容器中跨多个列显示其列表项，而延迟水平网格则会在水平轴上有相同的行为。"
            )
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp),
        ) {
            items(DataRepository.providePictures()) { item ->
                Image(
                    painter = painterResource(id = item), contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .aspectRatio(1.75f),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}