package com.example.newproject.view.ui.base

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.newproject.App
import com.example.newproject.dialog.AlertDialog
import com.example.newproject.util.DialogUtil
import com.example.newproject.BR
import com.example.newproject.view.ui.login.LoginActivity
import com.example.newproject.view.ui.splash.SplashActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), BaseNavigator,
    ToolbarNavigator {

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): BaseViewModel?
    abstract fun init()
    lateinit var mBinding: T
    var progresDialog: ProgressDialog? = null

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var firebaseDatabase :FirebaseDatabase
    lateinit var reference : DatabaseReference
    lateinit var firebaseUser : FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //스플래쉬가 있는 화면에선 이 코드를 실행 시켜야됨
        if (this !is SplashActivity) {
            App.activities.add(this)
        }
        mBinding = DataBindingUtil.setContentView(this@BaseActivity, getLayoutId())
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.view, this)
        initViewModel(getViewModel())
        //firebase 설정
        init()
    }


    override fun onDestroy() {
        App.activities.remove(this)
        progresDialog?.dismiss()
        alertDialog?.dismiss()
        selectableDialog?.dismiss()
        super.onDestroy()
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
        progresDialog?.dismiss()
        progresDialog = ProgressDialog(this)
        progresDialog?.show()
    }


    override fun showAlertDialog(
        content: String,
        onConfirmClick: (() -> Unit)?
    ): AlertDialog? {
        alertDialog = DialogUtil.showAlertDialog(this, content, onConfirmClick, alertDialog)
        return alertDialog
    }

    override fun showSelectableDialog(
        content: String,
        onConfirmClick: (() -> Unit)?,
        onCancelClick: (() -> Unit)?
    ): AlertDialog? {
        selectableDialog = DialogUtil.showSelectableDialog(
            this,
            content,
            onConfirmClick,
            onCancelClick,
            selectableDialog
        )
        return selectableDialog
    }

    override fun showAlertDialog(
        content: Int,
        onConfirmClick: (() -> Unit)?
    ): AlertDialog? {
        return DialogUtil.showAlertDialog(this, getString(content), onConfirmClick, alertDialog)
    }


    override fun showSelectableDialog(
        content: Int,
        onConfirmClick: (() -> Unit)?,
        onCancelClick: (() -> Unit)?
    ): AlertDialog? {
        return showSelectableDialog(getString(content), onConfirmClick, onCancelClick)
    }


    override fun finish() {
        super<AppCompatActivity>.finish()
    }


    var alertDialog: AlertDialog? = null
    var selectableDialog: AlertDialog? = null

    override fun showToast(string: Int) {
        Toast.makeText(this, getString(string), Toast.LENGTH_SHORT).show()
    }

    override fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun onLeftButtonClick() {
        finish()
    }

    override fun onRightButtonClick() {}

    override fun onRightTextClick(){}


    open fun logOut() {
        App.activities.filter { !(it is LoginActivity) }.forEach { finish() }
        getViewModel()?.setToken(null)
    }


    override fun onResume() {
        super.onResume()
        App.currentActivity = this
    }

    override fun showLoginDialog() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun gotoImageDetail(imageUrl: String?) {
//        imageUrl?.let {
//            startActivity(Intent(this, ImageDetailActivity::class.java).apply {
//                putExtra(ImageDetailActivity.DATA_IMAGE_URL, it)
//            })
//        }
    }
}