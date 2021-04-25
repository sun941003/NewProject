package com.example.newproject.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.example.newproject.App
import com.example.newproject.R

@RequiresApi(Build.VERSION_CODES.M)
class IssueListCustomView : View {

    var mainPaint: Paint
    var paint1 : Paint
    var paint2 : Paint
    var paint3 : Paint
    var paint4 : Paint
    var paint5 : Paint
    var radius = 0f
    lateinit var path : Path

    init {
        mainPaint = Paint()
        paint1 = Paint()
        paint2 = Paint()
        paint3 = Paint()
        paint4 = Paint()
        paint5 = Paint()
//        mainPaint.color = App.instance.getColor(R.color.gray_cecece)
        mainPaint.color =  App.instance.getColor(R.color.color_1)
        mainPaint.style = Paint.Style.FILL
        mainPaint.isAntiAlias = true



        paint5.color =  App.instance.getColor(R.color.color_5)
        paint5.style = Paint.Style.FILL
        paint5.isAntiAlias = true

        paint4.color = App.instance.getColor(R.color.color_4)
        paint4.style = Paint.Style.FILL
        paint4.isAntiAlias = true

        paint3.color = App.instance.getColor(R.color.color_3)
        paint3.style = Paint.Style.FILL
        paint3.isAntiAlias = true

        paint2.color = App.instance.getColor(R.color.color_2)
        paint2.style = Paint.Style.FILL
        paint2.isAntiAlias = true

        paint1.color = App.instance.getColor(R.color.color_1)
        paint1.style = Paint.Style.FILL
        paint1.isAntiAlias = true
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        radius = width/9.toFloat()
        canvas?.drawCircle(width/18*15.toFloat(),height/2.toFloat(),radius,paint5)
        canvas?.drawCircle(width/18*12.toFloat(),height/2.toFloat(),radius,paint4)
        canvas?.drawCircle(width/18*9.toFloat(),height/2.toFloat(),radius,paint3)
        canvas?.drawCircle(width/18*6.toFloat(),height/2.toFloat(),radius,paint2)
        canvas?.drawCircle(width/18*3.toFloat(),height/2.toFloat(),radius,paint1)
    }
}