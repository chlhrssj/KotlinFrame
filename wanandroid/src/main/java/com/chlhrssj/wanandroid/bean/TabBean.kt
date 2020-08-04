package com.chlhrssj.wanandroid.bean

/**
 * Create by rssj on 2020/8/4
 */

data class TabBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)