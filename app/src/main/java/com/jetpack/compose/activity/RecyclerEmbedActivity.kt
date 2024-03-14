package com.jetpack.compose.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jetpack.compose.base.AppPage
import com.jetpack.compose.data.DataRepository
import com.jetpack.compose.data.MediaEntity

/**
 * DragActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class RecyclerEmbedActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPage(title = "Recycler嵌入") {
                BuildRecycler()
            }
        }
    }
}

@Composable
fun BuildRecycler() {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            RecyclerView(ctx).apply {
                layoutManager = LinearLayoutManager(context)
                adapter = MyAdapter(DataRepository.provideRecycler())
            }
        }) {

    }
}

internal class MyAdapter(private val list: List<MediaEntity>) :
    RecyclerView.Adapter<MyAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ComposeView(parent.context))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = list[position]
        holder.compose.setContent {
            MediaItem(data)
        }
    }

    internal class MyHolder(val compose: ComposeView) : ViewHolder(compose)
}


@Composable
fun MediaItem(data: MediaEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color = Color(0x1A388E3C))
    ) {
        Image(
            painter = painterResource(id = data.cover), contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f),
            contentScale = ContentScale.Crop,
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            val (cover, name, actors, views, close) = createRefs()

            Image(
                painter = painterResource(id = data.cover), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(40.dp)
                    .aspectRatio(0.7f)
                    .clip(RoundedCornerShape(5.dp))
                    .constrainAs(cover) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = data.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3B424C),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .constrainAs(name) {
                        top.linkTo(cover.top)
                        start.linkTo(cover.end)
                    }
            )

            Text(
                text = data.actors?.joinToString(" ") ?: "暂无演员",
                fontSize = 14.sp,
                color = Color(0xFFA0A4A9),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .constrainAs(actors) {
                        start.linkTo(name.start)
                        bottom.linkTo(cover.bottom)
                    }
            )

            Text(
                text = data.views ?: "暂无观看数据",
                fontSize = 14.sp,
                color = Color(0xFFA0A4A9),
                modifier = Modifier
                    .constrainAs(views) {
                        bottom.linkTo(actors.bottom)
                        end.linkTo(parent.end)
                    }
            )
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                tint = Color(0xFF9B5503),
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(close) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .clickable {}
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            data.tag?.let { tag ->
                Row(
                    modifier = Modifier
                        .background(color = Color(0X33F18E1A), shape = RoundedCornerShape(10.dp))
                        .padding(start = 8.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = null,
                        tint = Color(0xFF9B5503),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = tag,
                        color = Color(0xFF9B5503),
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color(0xFF9B5503),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            data.talks?.let { talks ->
                Row(
                    modifier = Modifier
                        .padding(start = if (data.tag.isNullOrEmpty()) 0.dp else 10.dp)
                        .background(color = Color(0X33F18E1A), shape = RoundedCornerShape(10.dp))
                        .padding(start = 8.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color(0xFF9B5503),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = talks,
                        color = Color(0xFF9B5503),
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color(0xFF9B5503),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediaItemPreview() {
    MediaItem(data = DataRepository.provideRecycler()[0])
}