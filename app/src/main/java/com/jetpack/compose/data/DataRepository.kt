package com.jetpack.compose.data

import com.jetpack.compose.R
import com.jetpack.compose.activity.AnimationEffectsActivity
import com.jetpack.compose.activity.BasicTextActivity
import com.jetpack.compose.activity.BasicUiActivity
import com.jetpack.compose.activity.BroadcastReceiverActivity
import com.jetpack.compose.activity.ConstraintBarrierActivity
import com.jetpack.compose.activity.ConstraintChainStyleActivity
import com.jetpack.compose.activity.ConstraintLayoutActivity
import com.jetpack.compose.activity.ContentSlotActivity
import com.jetpack.compose.activity.DragActivity
import com.jetpack.compose.activity.GestureDetectActivity
import com.jetpack.compose.activity.HorizontalGridActivity
import com.jetpack.compose.activity.ImageBlurActivity
import com.jetpack.compose.activity.ImageColorFilterActivity
import com.jetpack.compose.activity.ImageColorMatrixActivity
import com.jetpack.compose.activity.IntrinsicHeightActivity
import com.jetpack.compose.activity.LocalImageActivity
import com.jetpack.compose.activity.NativeEmbedActivity
import com.jetpack.compose.activity.NetworkImageActivity
import com.jetpack.compose.activity.RecyclerEmbedActivity
import com.jetpack.compose.activity.SelectionContainerActivity
import com.jetpack.compose.activity.TextClickableActivity
import com.jetpack.compose.activity.TextFieldActivity
import com.jetpack.compose.activity.TransformableActivity
import com.jetpack.compose.activity.VerticalGridActivity

/**
 * DataRepository
 *
 * @author : zhen51.wang
 * @date : 2023/8/9/009
 */
object DataRepository {
    fun providePictures() = listOf(
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
    )

    fun index() = listOf(
        IndexEntity("RecyclerView", RecyclerEmbedActivity::class.java),
        IndexEntity("广播", BroadcastReceiverActivity::class.java),
        IndexEntity("原生内嵌", NativeEmbedActivity::class.java),
        IndexEntity("多点触控", TransformableActivity::class.java),
        IndexEntity("原生高斯模糊", AnimationEffectsActivity::class.java),
        IndexEntity("拖动", DragActivity::class.java),
        IndexEntity("手势检测", GestureDetectActivity::class.java),
        IndexEntity("图片模糊", ImageBlurActivity::class.java),
        IndexEntity("图片滤镜", ImageColorMatrixActivity::class.java),
        IndexEntity("色调调节", ImageColorFilterActivity::class.java),
        IndexEntity("基本控件", BasicUiActivity::class.java),
        IndexEntity("网络图片", NetworkImageActivity::class.java),
        IndexEntity("本地图片", LocalImageActivity::class.java),
        IndexEntity("基本文本", BasicTextActivity::class.java),
        IndexEntity("文本输入", TextFieldActivity::class.java),
        IndexEntity("文本点击", TextClickableActivity::class.java),
        IndexEntity("文本选择", SelectionContainerActivity::class.java),
        IndexEntity("Constraint链", ConstraintChainStyleActivity::class.java),
        IndexEntity("Constraint屏障", ConstraintBarrierActivity::class.java),
        IndexEntity("ConstraintLayout", ConstraintLayoutActivity::class.java),
        IndexEntity("固有特性", IntrinsicHeightActivity::class.java),
        IndexEntity("内容槽", ContentSlotActivity::class.java),
        IndexEntity("横向Grid", HorizontalGridActivity::class.java),
        IndexEntity("纵向Grid", VerticalGridActivity::class.java),
    )

    fun provideRecycler() = listOf(
        MediaEntity(
            cover = R.mipmap.video_long_season,
            name = "漫长的季节",
            actors = listOf("范伟 秦昊 探寻真相 侦破悬案"),
            tag = "悬疑剧榜第8名",
            views = "690万在追"
        ),
        MediaEntity(
            cover = R.mipmap.video_quedaomen,
            name = "鹊刀门传奇",
            actors = listOf("赵本山 宋小宝 宋晓峰 古装喜剧"),
            tag = "喜剧剧榜第2名",
            views = "91万在追"
        ),
        MediaEntity(
            cover = R.mipmap.video_snow,
            name = "雪中悍刀行",
            actors = listOf("张若昀 李庚希 英雄成长"),
            tag = null,
            talks = "讨论破100万",
            views = "785万在追"
        ),
        MediaEntity(
            cover = R.mipmap.video_never_say_never,
            name = "八角笼中",
            actors = listOf("王宝强 陈永胜 体育竞技"),
            tag = "电影热播榜第4名",
            talks = "讨论破10万",
            views = "108万在追"
        ),
        MediaEntity(
            cover = R.mipmap.video_qingyunian,
            name = "庆余年",
            actors = listOf("张若昀 李沁 英雄成长 小说改编"),
            tag = "传奇剧榜第1名",
            views = "530万在追"
        ),
        MediaEntity(
            cover = R.mipmap.video_magic_phone,
            name = "魔幻手机2：傻妞归来",
            actors = listOf("李滨 舒畅 奇幻喜剧 正邪对抗"),
            tag = "傻妞再次拯救地球和人类",
            views = "102万在追"
        ),
    )
}