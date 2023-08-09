package com.jetpack.compose

/**
 * DataRepository
 *
 * @author : zhen51.wang
 * @date : 2023/8/9/009
 */
object DataRepository {
    fun provideMessageCards() = listOf(
        Message(
            "Colleague",
            "Hey, take a look at Jetpack Compose, it's great! We toggle the expand variable when we click on this Column"
        ),
        Message(
            "Colleague",
            "Hey, take a look at Jetpack Compose, it's great! We toggle the expand variable when we click on this Column"
        ),
        Message(
            "Colleague",
            "Hey, take a look at Jetpack Compose, it's great! We toggle the expand variable when we click on this Column"
        ),
    )

    fun providePictures() = listOf(
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
        R.mipmap.banner,
    )
}