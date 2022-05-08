package com.example.newproject.view.ui.test_keyboard

import com.example.newproject.R
import com.example.newproject.databinding.ActivityKeyboardBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class KeyboardActivity : BaseActivity<ActivityKeyboardBinding>() {
    val vm : SplashViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_keyboard

    override fun getViewModel(): BaseViewModel? =vm

    override fun init() {

    }
}