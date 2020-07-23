package luyao.wanandroid.model.prefs

import com.chlhrssj.basecore.constant.BaseApp
import com.chlhrssj.basecore.delegate.SharePreferenceDelegate
import com.chlhrssj.projectcore.base.MyApp
import com.chlhrssj.wanandroid.bean.UserBean
import com.chlhrssj.wanandroid.constant.KV_USER
import com.google.gson.Gson

/**
 * Create by rssj on 2020/7/21
 */
class UserPrefs private constructor() {

    /**
     * 双重校验单例模式
     */
    companion object {
        val instance: UserPrefs by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UserPrefs()
        }
    }

    private var user by SharePreferenceDelegate(KV_USER, "")

    fun setUser(userBean: UserBean) {
        user = Gson().toJson(userBean)
    }

    fun getUser() : UserBean? {
        return Gson().fromJson(user, UserBean::class.java)
    }

    fun isLogin() : Boolean {
        return user.isNotEmpty()
    }

}