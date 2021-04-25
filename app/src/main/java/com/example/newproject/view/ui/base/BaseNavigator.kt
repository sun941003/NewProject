package com.example.newproject.view.ui.base

import com.example.newproject.dialog.AlertDialog


interface BaseNavigator {
    fun showAlertDialog(
        content: String,
        onConfirmClick: (() -> Unit)? = null
    ): AlertDialog?

    fun showSelectableDialog(
        content: String,
        onConfirmClick: (() -> Unit)? = null,
        onCancelClick: (() -> Unit)? = null
    ): AlertDialog?

    fun showAlertDialog(
        content: Int,
        onConfirmClick: (() -> Unit)? = null
    ): AlertDialog?

    fun showSelectableDialog(
        content: Int,
        onConfirmClick: (() -> Unit)? = null,
        onCancelClick: (() -> Unit)? = null
    ): AlertDialog?

    fun showToast(string: Int)
    fun showToast(string: String)
    fun finish()
    fun showProgress()
    fun dismissProgress()
    fun showLoginDialog()
}