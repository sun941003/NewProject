package com.example.newproject.view.ui.sign_up

import com.example.newproject.R
import com.example.newproject.databinding.ActivitySignUpBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    val vm :SignUpViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_sign_up

    override fun getViewModel(): BaseViewModel? =vm

    override fun init() {

    }
}