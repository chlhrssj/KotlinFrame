package com.chlhrssj.basecore.base.bean

/**
 * Create by rssj on 2020-01-17
 */

interface BaseBean<T>{
    var data: T
    var errorCode: Int
    var errorMsg: String
}