package com.chlhrssj.basecore.base.ui.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


/**
 * Create by rssj on 2020/6/1
 */
abstract class BaseViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    private fun launchOnIO(block: suspend CoroutineScope.() -> Unit) {
        ioScope.launch { block() }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
