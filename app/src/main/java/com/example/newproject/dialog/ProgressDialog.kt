package com.example.newproject.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.example.newproject.R
import com.example.newproject.databinding.DialogProgressBinding

class ProgressDialog(val context: Activity) : Dialog(context) {
    val mBinding by lazy {
        DataBindingUtil.inflate<DialogProgressBinding>(
            LayoutInflater.from(context),
            R.layout.dialog_progress,
            null,
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(mBinding.root)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }

    override fun show() {
        if (!context.isFinishing && !context.isDestroyed)
            super.show()
    }
}
