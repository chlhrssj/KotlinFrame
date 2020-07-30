package com.chlhrssj.wanandroid.ui.login

import com.chlhrssj.basecore.base.bean.BaseResult
import com.chlhrssj.basecore.base.event.BaseEvent
import com.chlhrssj.basecore.base.ui.mvvm.BaseViewModel
import com.chlhrssj.basecore.constant.BaseApp
import com.chlhrssj.basecore.util.ToastUtils
import com.chlhrssj.wanandroid.constant.KV_LOGIN
import com.chlhrssj.wanandroid.constant.KV_LOGOUT
import com.chlhrssj.wanandroid.repository.AccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import luyao.wanandroid.model.prefs.UserPrefs
import org.greenrobot.eventbus.EventBus

/**
 * Create by rssj on 2020/7/24
 */
class LoginViewModel : BaseViewModel() {

    private val accountRepository by lazy { AccountRepository() }

    fun doLogin(
        userName: String,
        password: String,
        successBlock: suspend CoroutineScope.() -> Unit
    ) {
        launchOnUI {
            when (val result = accountRepository.execLogin(userName, password)) {
                is BaseResult.Success -> {
                    UserPrefs.instance.setUser(result.data)
                    EventBus.getDefault().post(BaseEvent(KV_LOGIN))
                    successBlock.invoke(this)
                }
                is BaseResult.Error -> ToastUtils.showShort(
                    BaseApp.getApp(),
                    result.exception.toString()
                )
            }
        }
    }

}