package com.chlhrssj.basecore.http

import com.orhanobut.logger.Logger
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Create by rssj on 2020-01-02
 */
class HttpLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Logger.d(message)
    }
}