package com.chlhrssj.wanandroid.api

import com.chlhrssj.basecore.base.bean.BaseBean
import com.chlhrssj.wanandroid.bean.ArticleListBean
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by luyao
 * on 2018/3/13 14:33
 */
interface WanService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): BaseBean<ArticleListBean>

}