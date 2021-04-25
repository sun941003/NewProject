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
            setContents(content)
            this.onCancelClick = onCancelClick
            this.onConfirmClick = onConfirmClick
            show()
        }
    }
}