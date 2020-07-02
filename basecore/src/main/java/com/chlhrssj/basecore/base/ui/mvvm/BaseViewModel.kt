package com.chlhrssj.basecore.base.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * Create by rssj on 2020/6/1
 */
abstract class BaseViewModel : ViewModel() {

    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

}
