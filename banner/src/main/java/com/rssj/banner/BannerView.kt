package com.rssj.banner

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2

/**
 * Create by rssj on 2020/9/28
 */
class BannerView : RelativeLayout {

    private val DEFAULT_AUTO_TIME: Long = 2500

    private var isAutoPlay: Boolean = false
    private var autoPlayTime = DEFAULT_AUTO_TIME

    private val viewPager2: ViewPager2

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        viewPager2 = ViewPager2(context);
    }

}