package com.chlhrssj.wanandroid.ui.find

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.bean.ArticleListBean
import com.chlhrssj.wanandroid.bean.ProjectBean

/**
 * Create by rssj on 2020/8/5
 */
class FindAdapter(list: List<ProjectBean.Data>) : BaseQuickAdapter<ProjectBean.Data, BaseViewHolder>(
    R.layout.item_find, list) {
    override fun convert(helper: BaseViewHolder, item: ProjectBean.Data?) {
        helper.run {
            setText(R.id.tv_title, item?.title)
            setText(R.id.tv_desc, item?.desc)
            setText(R.id.tv_time, item?.shareDate.toString())
            Glide.with(mContext)
                .load(item?.envelopePic)
                .into(getView<ImageView>(R.id.iv_img))
            addOnClickListener(R.id.iv_img)
        }
    }
}