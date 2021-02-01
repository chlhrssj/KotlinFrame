package com.chlhrssj.wanandroid.ui.more.rx

import android.os.Bundle
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.databinding.ActivityDrawBinding
import com.chlhrssj.wanandroid.databinding.ActivityRxBinding
import com.gyf.immersionbar.ktx.immersionBar

class RxActivity : BaseVcActivity<ActivityRxBinding>() {

    override fun initBinding(): ActivityRxBinding = ActivityRxBinding.inflate(layoutInflater)

    override fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }
    }

    override fun initView() {
        binding.run {
            toolbar.setNavigationOnClickListener { finish() }
        }
        binding.btnSubscribe.setOnClickListener {
            MyObservable.create(object : MyObservableOnSubscribe<Int> {
                override fun setObserver(emitter: MyObserver<Int>) {
                    binding.tvLog.text = ("上游发送数据:10 11 12")
                    emitter.onNext(10)
                    emitter.onNext(11)
                    emitter.onNext(12)
                }
            })
                .map { event ->
                    return@map "$event 转换后 +++"
                }
                .setObserver(object : MyObserver<String> {
                    override fun onSubscribe() {
                        binding.tvLog.text = "${binding.tvLog.text} \nonSubscribe"
                    }

                    override fun onNext(item: String) {
                        binding.tvLog.text = "${binding.tvLog.text} \n下游接收到数据:$item"
                    }

                    override fun onError(e: Throwable) {}
                    override fun onComplete() {}
                })
        }
    }

}