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
}