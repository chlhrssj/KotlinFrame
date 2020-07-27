package com.chlhrssj.wanandroid.repository

import com.chlhrssj.basecore.base.bean.BaseResult
import com.chlhrssj.basecore.base.ui.mvvm.BaseRepository
import com.chlhrssj.wanandroid.api.WanRetrofitClient
import com.chlhrssj.wanandroid.bean.ArticleListBean
import com.chlhrssj.wanandroid.bean.BannerBean
import com.chlhrssj.wanandroid.bean.UserBean
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope

/**
 * Create by rssj on 2020/7/24
 */
class AccountRepository : BaseRepository() {

    suspend fun execLogin(userName: String, password: String) : BaseResult<UserBean>{
        val response = WanRetrofitClient.service.login(userName, password)

        return executeResponse(response)
    }

}