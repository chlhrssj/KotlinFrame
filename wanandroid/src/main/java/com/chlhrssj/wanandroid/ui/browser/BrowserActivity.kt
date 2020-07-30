package com.chlhrssj.wanandroid.ui.browser

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebSettings.LOAD_NO_CACHE
import android.webkit.WebView
import android.webkit.WebViewClient
import com.chlhrssj.basecore.base.ui.mvc.BaseVcActivity
import com.chlhrssj.wanandroid.R
import com.chlhrssj.wanandroid.constant.KV_URL
import com.chlhrssj.wanandroid.databinding.ActivityBrowserBinding
import com.gyf.immersionbar.ktx.immersionBar

class BrowserActivity : BaseVcActivity<ActivityBrowserBinding>() {

    lateinit var url: String

    override fun initImmersionBar() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimary)
        }
    }

    override fun initBinding(): ActivityBrowserBinding = ActivityBrowserBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()

        url = intent.getStringExtra(KV_URL)
    }

    override fun initView() {
        super.initView()

        binding.run {
            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }

            webView.run {
                webViewClient = object : WebViewClient() {

                    override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                        super.onPageStarted(p0, p1, p2)
                        progressBar.visibility = View.VISIBLE
                    }

                    override fun onPageFinished(p0: WebView?, p1: String?) {
                        super.onPageFinished(p0, p1)
                        progressBar.visibility = View.GONE
                    }
                }
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(p0: WebView?, p1: Int) {
                        super.onProgressChanged(p0, p1)
                        progressBar.progress = p1
                        Log.e("browser",p1.toString())
                    }

                    override fun onReceivedTitle(p0: WebView?, p1: String?) {
                        super.onReceivedTitle(p0, p1)
                        p1?.let { toolbar.title = p1 }
                    }

                }

                settings.run {
                    javaScriptEnabled = true
                    javaScriptCanOpenWindowsAutomatically = true
                    allowFileAccess = true
                    layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
                    setSupportZoom(true)
                    builtInZoomControls = true
                    useWideViewPort = true
                    setSupportMultipleWindows(true)
                    loadWithOverviewMode = true
                    setAppCacheEnabled(true)
                    // webSetting.setDatabaseEnabled(true);
                    // webSetting.setDatabaseEnabled(true);
                    domStorageEnabled = true
                    setGeolocationEnabled(true)
                    setAppCacheMaxSize(Long.MAX_VALUE)
                    pluginState = WebSettings.PluginState.ON_DEMAND
                    cacheMode = LOAD_NO_CACHE
                }
            }

            webView.loadUrl(url)
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}