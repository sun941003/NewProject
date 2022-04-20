package com.example.newproject.view.ui.splash

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewModelScope
import com.example.newproject.model.model.JsonData
import com.example.newproject.view.ui.base.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class SplashViewModel : BaseViewModel() {
    lateinit var splashNavigator: SplashNavigator

    fun initApp() {
        if (getToken().value.isNullOrEmpty()) {
            splashNavigator.gotoLogin()
        } else {
            splashNavigator.gotoMain()
        }
    }

    /*
        coroutine ->
        async는 반환값이 있을때 사용 , launch는 그냥 일반 실행
     */

}