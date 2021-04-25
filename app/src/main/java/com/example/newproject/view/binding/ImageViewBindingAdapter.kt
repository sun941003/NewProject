package com.example.newproject.view.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object ImageViewBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["loadGlide","defaultImage"],requireAll = false)
    fun loadGlideFromUrl(view:ImageView,url:String?,defaultImage : Drawable?){
        url?.let {
            Glide.with(view.context).load(it).transition(DrawableTransitionOptions.withCrossFade())
                .apply {
                    defaultImage?.let { this.error(it) }
                }.into(view)
        } ?:run{
            defaultImage?.let { view.setImageDrawable(it) }
        }
    }
    //bindingadapter에 있다고 매개변수들을 전부 사용해야 하는건 아님 -> requireAll이 false이면 그 중 적어도 하나만 있어도 실행된다 이런 정도로 설명되어 있었음 (바인딩어댑터 디벨로퍼에서 requireAll 타고 영문 공식 문서로 들어가면 확인가능)
}