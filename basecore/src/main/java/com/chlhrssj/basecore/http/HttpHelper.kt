package com.chlhrssj.basecore.http

import android.net.sip.SipErrorCode.TIME_OUT
import android.util.SparseArray
import com.chlhrssj.basecore.BuildConfig
import com.chlhrssj.basecore.constant.BaseApp
import com.chlhrssj.basecore.util.NetUtils
import com.orhanobut.logger.Logger
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * 多少种Host类型
 */
val H_TYPE_COUNT = 10

//读超时长，单位：毫秒
val H_READ_TIME_OUT = 30000
//连接时长，单位：毫秒
val H_CONNECT_TIME_OUT = 15000

/**
 * Create by rssj on 2020-01-02
 */
class HttpHelper private constructor(hostType: Int){

    companion object {
        //设缓存有效期为3天
        private val CACHE_STALE_SEC = (60 * 60 * 24 * 3).toLong()
        //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
        //max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
        private val CACHE_CONTROL_CACHE = "only-if-cached, max-stale=$CACHE_STALE_SEC"
        //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
        //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
        private val CACHE_CONTROL_AGE = "max-age=0"
        //sparsearray 比 hashmap 更优化内存
        private val sRetrofitManager = SparseArray<HttpHelper>(H_TYPE_COUNT)
        fun getDefault(hostType: Int): Retrofit {
            var retrofitManager: HttpHelper? = sRetrofitManager.get(hostType)
            if (retrofitManager == null) {
                retrofitManager = HttpHelper(hostType)
                sRetrofitManager.put(hostType, retrofitManager)
                return retrofitManager.retrofit
            }

            return retrofitManager.retrofit
        }
    }

    private var retrofit: Retrofit
    private var okHttpClient: OkHttpClient

    init {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            //开启Log
            initLogInterceptor(builder)
        }
        //添加缓存
        initCacheInterceptor(builder)

        builder.readTimeout(H_READ_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            .connectTimeout(H_CONNECT_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)

        okHttpClient = builder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(getHost(hostType))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    /**
     * 添加缓存拦截器
     */
    fun initCacheInterceptor(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        //缓存
        val cacheFile = File(BaseApp.getApp().getCacheDir(), "cache")
        val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong()) //100Mb
        builder.addNetworkInterceptor(CacheNetworkInterceptor())
            .addInterceptor(CacheInterceptor(60 * 60 * 24))
            .cache(cache)
        return builder
    }

    /**
     * 添加打印日志拦截器
     */
    fun initLogInterceptor(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val logInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addNetworkInterceptor(logInterceptor)
        return builder
    }

    /**
     * 根据网络状况获取缓存的策略
     * 使用方法:在api server 中添加请求头 @Header("Cache-Control") String cacheControl ,在调用传入getCacheControl()
     */
    fun getCacheControl(): String {
        return if (NetUtils.isNetConnected(BaseApp.getApp())) CACHE_CONTROL_AGE else CACHE_CONTROL_CACHE
    }

    class CacheNetworkInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val originalResponse = chain.proceed(request)
            val maxAge = 0    // 在线缓存,单位:秒
            return originalResponse.newBuilder()
                .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        }
    }

    class CacheInterceptor(private val maxStale: Int) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            if (!NetUtils.isNetConnected(BaseApp.getApp())) {            //如果断网 这里返回 缓存数据 直接结束这次访问
                val tempCacheControl = CacheControl.Builder()
                    .onlyIfCached()
                    .maxStale(maxStale, TimeUnit.SECONDS)
                    .build()

                request = request.newBuilder()
                    .cacheControl(tempCacheControl)
                    .build()

            }
            return chain.proceed(request)
        }
    }

    class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Logger.d(message)
        }
    }


}