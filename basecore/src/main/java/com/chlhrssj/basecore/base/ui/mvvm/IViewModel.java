package com.chlhrssj.basecore.base.ui.mvvm;

/**
 * Create by rssj on 2020/3/19
 * VM接口
 */
interface IViewModel {

    /**
     * 初始化
     */
    void init();

    /**
     * 初始化仓库
     */
    void initRepository();

    /**
     * 结束
     */
    void destroy();
}
