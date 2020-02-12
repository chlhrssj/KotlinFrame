package com.chlhrssj.basecore.http

import com.chlhrssj.basecore.constant.KV_ISDEBUG


/**
 * Create by rssj on 2020-01-02
 */
private val H_BASEURL = if (KV_ISDEBUG) "https://www.wanandroid.com/" else "https://www.wanandroid.com/"

val H_BASETYPE = 0

/**
 * 获取对应的host
 *
 * @param hostType host类型
 * @return host
 */
fun getHost(hostType: Int): String {
    val host = ""
    when (hostType) {
        H_BASETYPE -> return H_BASEURL
    }
    return host
}