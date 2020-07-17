package com.chlhrssj.wanandroid.repository

import com.chlhrssj.basecore.base.bean.BaseResult
import com.chlhrssj.basecore.base.ui.mvvm.BaseRepository
import com.chlhrssj.wanandroid.api.WanRetrofitClient
import com.chlhrssj.wanandroid.bean.ArticleListBean

/**
 * Create by rssj on 2020/7/17
 */
class ArticleRepository : BaseRepository() {

    suspend fun getHomeArticleList(page: Int) : BaseResult<ArticleListBean> {
        return safeApiCall(call = { executeResponse(WanRetrofitClient.service.getHomeArticles(page)) }, errorMessage = "")
    }

}