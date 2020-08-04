package com.chlhrssj.wanandroid.repository

import com.chlhrssj.basecore.base.bean.BaseResult
import com.chlhrssj.basecore.base.ui.mvvm.BaseRepository
import com.chlhrssj.wanandroid.api.WanRetrofitClient
import com.chlhrssj.wanandroid.bean.ProjectBean
import com.chlhrssj.wanandroid.bean.TabBean

/**
 * Create by rssj on 2020/8/4
 */
class ProjectRepository : BaseRepository() {

    suspend fun getTab() : BaseResult<List<TabBean>> {
        return safeApiCall(call = { executeResponse(WanRetrofitClient.service.getTab()) }, errorMessage = "")
    }

    suspend fun getProject(page: Int, cid: String) : BaseResult<ProjectBean> {
        return safeApiCall(call = { executeResponse(WanRetrofitClient.service.getProject(page, cid)) }, errorMessage = "")
    }

}