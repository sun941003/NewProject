package com.example.newproject.view.ui.splash

import com.example.newproject.view.ui.base.BaseViewModel
import com.google.firebase.ktx.Firebase

class SplashViewModel : BaseViewModel() {
    lateinit var splashNavigator: SplashNavigator

    fun initApp() {
        if (getToken().value.isNullOrEmpty()) {
            splashNavigator.gotoLogin()
        } else {
            splashNavigator.gotoMain()
        }
    }

}