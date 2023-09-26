package com.jetpack.compose.data

/**
 * MediaEntity
 *
 * @author : zhen51.wang
 * @date : 2023/9/26/026
 */
data class MediaEntity(
    val cover: Int,
    val name: String,
    val actors: List<String>? = null,
    val tag: String,
)
