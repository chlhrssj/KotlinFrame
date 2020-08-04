package com.chlhrssj.wanandroid.ui.home

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chlhrssj.basecore.base.bean.BaseResult
import com.chlhrssj.basecore.base.ui.mvvm.BaseViewModel
import com.chlhrssj.basecore.constant.BaseApp
import com.chlhrssj.basecore.util.ToastUtils
import com.chlhrssj.wanandroid.bean.ArticleListBean
import com.chlhrssj.wanandroid.bean.BannerBean
import com.chlhrssj.wanandroid.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Create by rssj on 2020/7/8
 */
class HomeViewModel : BaseViewModel() {

    val artLiveData by lazy { MutableLiveData<ArticleListBean>() }
    val bannerLiveData by lazy { MutableLiveData<List<BannerBean>>() }

    var page = 0
    private val articleRepository: ArticleRepository by lazy { ArticleRepository() }

    fun getList(isRefresh: Boolean) {
        page = if (isRefresh) 0 else page + 1
        launchOnIO {
            when(val result = articleRepository.getHomeArticleList(page)) {
                is BaseResult.Success -> artLiveData.postValue(result.data)
                is BaseResult.Error -> withContext(Dispatchers.Main) {ToastUtils.showShort(BaseApp.getApp(), result.exception.toString())}
            }

        }
    }

    fun getBanner() {
        launchOnIO {
            when(val result = articleRepository.getBanner()) {
                is BaseResult.Success -> bannerLiveData.postValue(result.data)
                is BaseResult.Error -> withContext(Dispatchers.Main) {ToastUtils.showShort(BaseApp.getApp(), result.exception.toString())}
            }
        }
    }

}