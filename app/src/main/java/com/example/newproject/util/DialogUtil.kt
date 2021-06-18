package com.example.newproject.util

import android.app.Activity
import android.content.Context
import com.example.newproject.dialog.AlertDialog

object DialogUtil {
    fun showAlertDialog(
        context: Context,
        content: String,
        onConfirmClick: (() -> Unit)? = null,
        preAlert: AlertDialog?
    ): AlertDialog {
        preAlert?.dismiss()
        return AlertDialog(context as Activity).apply {
            mBinding.tvConfirm.text = "확인"
            setContents(content)
            this.onConfirmClick = onConfirmClick
            show()
        }
    }

    fun showSelectableDialog(
        context: Context,
        content: String,
        onConfirmClick: (() -> Unit)? = null,
        onCancelClick: (() -> Unit)? = null,
        selectableDialog: AlertDialog?
    ): AlertDialog {
        selectableDialog?.dismiss()
        return AlertDialog(context as Activity, true).apply {
            mBinding.tvConfirm.text = "확인"
            mBinding.tvCancel.text = "취소"
            setContents(content)
            this.onCancelClick = onCancelClick
            this.onConfirmClick = onConfirmClick
            show()
        }
    }
}