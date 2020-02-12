package com.chlhrssj.wanandroid.api

import com.chlhrssj.wanandroid.bean.HomeListBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Create by rssj on 2020-01-16
 */
interface ApiService {
    /**
     * 首页列表
     */
    @GET("article/listproject/{page}/json")
    fun getHomeList(@Path("page") page: String): Observable<HomeListBean>
}