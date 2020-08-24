package com.chlhrssj.wanandroid.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Create by rssj on 2020/8/21
 */
class DrawingBoard : View {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {}

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {}

    var paint: Paint = Paint()
    var path: Path = Path()

    init {
        paint.run {
            strokeWidth = 5f
            color = Color.RED
            style = Paint.Style.STROKE
            setOnTouchListener(object : View.OnTouchListener {

                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            path.moveTo(event.x, event.y)
                            invalidate()
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val historySize: Int = event.getHistorySize()
                            for (i in 0 until historySize) {
                                val historicalX: Float = event.getHistoricalX(i)
                                val historicalY: Float = event.getHistoricalY(i)
                                path.lineTo(historicalX, historicalY)
                            }

                            path.lineTo(event.x, event.y)
                            invalidate()
                        }
                        MotionEvent.ACTION_UP -> {
                            val historySize: Int = event.getHistorySize()
                            for (i in 0 until historySize) {
                                val historicalX: Float = event.getHistoricalX(i)
                                val historicalY: Float = event.getHistoricalY(i)
                                path.lineTo(historicalX, historicalY)
                            }

                            path.lineTo(event.x, event.y)
                            invalidate()
                        }
                        else -> {
                        }
                    }
                    return true
                }

            })
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        canvas.drawPath(path, paint)
    }


}