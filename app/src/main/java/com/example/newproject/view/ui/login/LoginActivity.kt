package com.example.newproject.view.ui.login

import android.content.Intent
import com.example.newproject.R
import com.example.newproject.databinding.ActivityLoginBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.main.MainActivity
import com.example.newproject.view.ui.sign_up.SignUpActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    val vm : LoginViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel? =vm

    override fun init() {

    }
    fun gotoSignUp(){
        startActivity(Intent(this,SignUpActivity::class.java))
    }
    fun gotoMain(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}