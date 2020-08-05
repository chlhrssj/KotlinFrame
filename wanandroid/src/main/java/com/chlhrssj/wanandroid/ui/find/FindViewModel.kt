package com.chlhrssj.wanandroid.ui.find

import androidx.lifecycle.MutableLiveData
import com.chlhrssj.basecore.base.bean.BaseResult
import com.chlhrssj.basecore.base.ui.mvvm.BaseViewModel
import com.chlhrssj.basecore.constant.BaseApp
import com.chlhrssj.basecore.util.ToastUtils
import com.chlhrssj.wanandroid.bean.ArticleListBean
import com.chlhrssj.wanandroid.bean.ProjectBean
import com.chlhrssj.wanandroid.bean.TabBean
import com.chlhrssj.wanandroid.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Create by rssj on 2020/7/13
 */
class FindViewModel : BaseViewModel() {

    private val projectRepository by lazy { ProjectRepository() }

    val tabLiveData = MutableLiveData<List<TabBean>>()
    val projectLiveData by lazy { MutableLiveData<ProjectBean>() }

    var page = 1

    fun getTab() {
        launchOnUI {
            when (val result = projectRepository.getTab()) {
                is BaseResult.Success -> tabLiveData.setValue(result.data)
                is BaseResult.Error -> ToastUtils.showShort(BaseApp.getApp(), result.exception.toString())
            }
        }
    }

    fun getProject(isRefresh: Boolean, cid: String) {page = if (isRefresh) 1 else page + 1
        launchOnUI {
            when(val result = projectRepository.getProject(page, cid)) {
                is BaseResult.Success -> projectLiveData.postValue(result.data)
                is BaseResult.Error -> ToastUtils.showShort(BaseApp.getApp(), result.exception.toString())
            }

        }}

}