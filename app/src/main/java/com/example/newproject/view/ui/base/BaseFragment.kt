package com.example.newproject.view.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.newproject.BR
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.newproject.dialog.AlertDialog
import com.example.newproject.dialog.ProgressDialog
import com.example.newproject.util.DialogUtil

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), BaseNavigator, ToolbarNavigator {
    companion object {
        val REQUEST_LOGIN: Int = 1
    }

    lateinit var mBinding: T
    var progresDialog: ProgressDialog? = null

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): BaseViewModel
    abstract fun init()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.view, this)
        initViewModel(getViewModel())
        init()
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        progresDialog?.dismiss()
        alertDialog?.dismiss()
        selectableDialog?.dismiss()
    }

    fun initViewModel(vm: BaseViewModel?) {
        vm?.let {
            it.baseNavigator = this
            mBinding.setVariable(BR.vm, vm)
        }
    }

    override fun dismissProgress() {
        progresDialog?.dismiss()
        progresDialog = null
    }

    override fun showProgress() {
        if (isAdded) {
            progresDialog?.dismiss()
            progresDialog = ProgressDialog(requireActivity())
            progresDialog?.show()
        }
    }


    override fun showAlertDialog(
        content: String,
        onConfirmClick: (() -> Unit)?
    ): AlertDialog? {
        if (isAdded) {
            alertDialog =
                DialogUtil.showAlertDialog(requireActivity(), content, onConfirmClick, alertDialog)
            return alertDialog
        }
        return null
    }

    override fun showSelectableDialog(
        content: String,
        onConfirmClick: (() -> Unit)?,
        onCancelClick: (() -> Unit)?
    ): AlertDialog? {
        if (isAdded) {
            selectableDialog = DialogUtil.showSelectableDialog(
                requireActivity(),
                content,
                onConfirmClick,
                onCancelClick,
                selectableDialog
            )
            return selectableDialog
        }
        return null
    }

    override fun showAlertDialog(
        content: Int,
        onConfirmClick: (() -> Unit)?
    ): AlertDialog? {
        return showAlertDialog(getString(content), onConfirmClick)
    }

    override fun showSelectableDialog(
        content: Int,
        onConfirmClick: (() -> Unit)?,
        onCancelClick: (() -> Unit)?
    ): AlertDialog? {
        return showSelectableDialog(getString(content), onConfirmClick, onCancelClick)
    }



    var alertDialog: AlertDialog? = null
    var selectableDialog: AlertDialog? = null

    override fun showToast(string: Int) {
        Toast.makeText(requireActivity(), getString(string), Toast.LENGTH_SHORT).show()
    }

    override fun showToast(string: String) {
        Toast.makeText(requireActivity(), string, Toast.LENGTH_SHORT).show()
    }

    override fun finish() {
        requireActivity().finish()
    }

    override fun onLeftButtonClick() {

    }
    override fun onRightButtonClick() {

    }

    override fun onRightTextClick() {

    }


    open fun logOut() {
        getViewModel().setToken(null)
    }

    override fun showLoginDialog() {
//        showSelectableDialog(R.string.need_login, {
//            requireActivity()?.let { startActivity(Intent(it, LoginType::class.java)) }
//        })
//    }
    }
}