package com.chlhrssj.wanandroid.ui.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.bean.ArticleListBean


/**
 * Create by rssj on 2020/7/16
 */
class HomeAdapter(list: List<ArticleListBean.Data>) : BaseQuickAdapter<ArticleListBean.Data, BaseViewHolder>(R.layout.item_home, list) {

    override fun convert(helper: BaseViewHolder, item: ArticleListBean.Data?) {
        helper.run {
            setText(R.id.tv_author, item?.shareUser)
            setText(R.id.tv_title, item?.title)
            setText(R.id.tv_time, item?.niceDate)
        }
    }


}