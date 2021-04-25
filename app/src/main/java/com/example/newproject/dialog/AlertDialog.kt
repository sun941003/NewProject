package com.example.newproject.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import com.example.newproject.R
import com.example.newproject.databinding.DialogAlertBinding

class AlertDialog(val context: Activity, val isCancelMode: Boolean = false) :
        Dialog(context, R.style.dialogTheme) {
    var onCancelClick: (() -> Unit)? = null
    var onConfirmClick: (() -> Unit)? = null
    val mBinding by lazy {
        DataBindingUtil.inflate<DialogAlertBinding>(
                LayoutInflater.from(context),
                R.layout.dialog_alert,
                null,
                false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setContentView(mBinding.root)
        mBinding.tvCancel.visibility = if (isCancelMode) View.VISIBLE else View.GONE
        mBinding.tvCancel.setOnClickListener {
            dismiss()
            onCancelClick?.invoke()
        }

        mBinding.tvConfirm.setOnClickListener {
            dismiss()
            onConfirmClick?.invoke()
        }
    }

    fun setContents(content: String) {
        mBinding.tvContent.text = content
    }

    override fun show() {
        if (!context.isFinishing && !context.isDestroyed)
            super.show()
    }
}