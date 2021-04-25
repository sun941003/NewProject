package com.example.newproject.view.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.databinding.ActivitySplashBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.login.LoginActivity
import com.example.newproject.view.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>(),SplashNavigator {
    val vm : SplashViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getViewModel(): BaseViewModel?=vm

    override fun init() {
        vm.splashNavigator = this

        //이게 푸시 알람 처럼 스플레시 화면 한번 거치는 화면에서 스택 관련해서 오류 생기면 한번 초기화 시키는 코드
//        App.activities.forEach{it.finish()}
//        App.activities.clear()
        Handler(Looper.getMainLooper()).postDelayed({vm.initApp()},1000)

    }

    override fun gotoMain() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun gotoLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
        showToast("로그인 화면 미구현")
    }
}