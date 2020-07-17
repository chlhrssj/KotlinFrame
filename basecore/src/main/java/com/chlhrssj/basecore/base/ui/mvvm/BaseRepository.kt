package com.chlhrssj.basecore.base.ui.mvvm

import com.chlhrssj.basecore.base.bean.BaseBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import com.chlhrssj.basecore.base.bean.BaseResult

/**
 * Create by rssj on 2020/7/2
 */
abstract class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> BaseBean<T>): BaseBean<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> BaseResult<T>, errorMessage: String): BaseResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            BaseResult.Error(IOException(errorMessage, e))
        }
    }

    suspend fun <T : Any> executeResponse(response: BaseBean<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                          errorBlock: (suspend CoroutineScope.() -> Unit)? = null): BaseResult<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                BaseResult.Error(IOException(response.errorMsg))
            } else {
                successBlock?.let { it() }
                BaseResult.Success(response.data)
            }
        }
    }

}