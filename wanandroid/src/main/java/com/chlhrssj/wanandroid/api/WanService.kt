package com.chlhrssj.wanandroid.api

import com.chlhrssj.basecore.base.bean.BaseBean
import com.chlhrssj.wanandroid.bean.*
import retrofit2.http.*


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

    @GET("/banner/json")
    suspend fun getBanner(): BaseBean<List<BannerBean>>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): BaseBean<UserBean>

    @GET("project/tree/json")
    suspend fun getTab(): BaseBean<List<TabBean>>

    @GET("project/list/{page}/json")
    suspend fun getProject(@Path("page") page: Int, @Field("cid") cid: String): BaseBean<ProjectBean>

}