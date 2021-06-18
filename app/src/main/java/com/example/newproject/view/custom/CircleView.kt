package com.example.newproject.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.view.marginBottom
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.databinding.ItemNumberBallBinding
import com.google.firebase.database.collection.LLRBNode

@RequiresApi(Build.VERSION_CODES.M)
class CircleView @JvmOverloads constructor(
    context: Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int=0
) : LinearLayout(context,attrs,defStyleAttr) {

    val paint  : Paint = Paint()
    var radius = 0f
    val centerNumber = MutableLiveData<Int>(0)
    lateinit var mBinding : ItemNumberBallBinding


    init {

//      val color1 = App.instance.getColor(R.color.color_1)
//        paint.style = Paint.Style.FILL
        initView()
    }

    fun initView(){
        mBinding = ItemNumberBallBinding.inflate(LayoutInflater.from(context),null, false)
        mBinding.lifecycleOwner = (context as LifecycleOwner)
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1f)
        mBinding.view = this
        mBinding.llContainer.layoutParams = lp
        mBinding.rtvNumber.setBackgroundColor(App.instance.getColor(R.color.color_1))
        addView(mBinding.root)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val wid = width.toFloat()
        val hei = height.toFloat()
        radius = wid/9
        canvas?.drawCircle(wid/18*15,hei/2,radius,paint)
    }

    fun setColorForm(sourceNumber : Int){
        centerNumber.value = sourceNumber
        if(sourceNumber<11){
            paint.color = App.instance.getColor(R.color.color_1)
        }else if (sourceNumber<21){
            paint.color = App.instance.getColor(R.color.color_2)
        }else if(sourceNumber<31){
            paint.color = App.instance.getColor(R.color.color_3)
        }else if(sourceNumber<41){
            paint.color = App.instance.getColor(R.color.color_4)
        }else{
            paint.color = App.instance.getColor(R.color.color_5)
        }

    }
}