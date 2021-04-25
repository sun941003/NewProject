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
class NicknameCustomView @JvmOverloads constructor(
    context: Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int=0
) : LinearLayout(context,attrs,defStyleAttr) {

    val paint1  : Paint
   val paint2  : Paint
   val paint3  : Paint
   val paint4  : Paint
    val paint5  : Paint
    var radius = 0f

    init {

       paint1  = Paint()
//      val color1 = App.instance.getColor(R.color.color_1)
        val color1 = Color.RED
        paint1.color = color1
        paint1.style = Paint.Style.FILL
       paint2  = Paint()
        paint3  = Paint()
        paint4  = Paint()
        paint5  = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val wid = width.toFloat()
        val hei = height.toFloat()
        radius = wid/9
        canvas?.drawCircle(wid/18*15,hei/2,radius,paint1)

    }

}