package com.chlhrssj.wanandroid.api

import android.app.PendingIntent.getService
import com.chlhrssj.basecore.http.BaseRetrofitClient
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.io.File


/**
 * Created by luyao
 * on 2018/3/13 15:45
 */
object WanRetrofitClient : BaseRetrofitClient() {

    val service by lazy { getService(WanService::class.java, WanService.BASE_URL) }


}