package com.chlhrssj.basecore.base.bean

/**
 * Create by rssj on 2020-01-17
 */

data class BaseBean<out T>(val errorCode: Int, val errorMsg: String, val data: T)