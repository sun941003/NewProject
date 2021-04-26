package com.example.newproject.view.ui.base

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newproject.App
import com.example.newproject.util.SharedPreferenceUtil
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

open class BaseViewModel : ViewModel() {
    companion object {
        val token = MutableLiveData<String?>(SharedPreferenceUtil.getToken())
        val userNickName = MutableLiveData("")
    }
    lateinit var baseNavigator: BaseNavigator



    fun setToken(token: String?) {
        SharedPreferenceUtil.saveToken(token)
        if (token == null) {
            userNickName.value = ""
        }
        Companion.token.value = token
    }

    fun getToken(): MutableLiveData<String?> {
        return token
    }

    fun getUserNickname(): MutableLiveData<String?> {
        return userNickName
    }

    fun checkPermssion(
        vararg permissionArray: String
    ): Boolean {
        var result: Int
        val permissionsNeeded: MutableList<String> =
            ArrayList()
        for (p in permissionArray) {
            result = ContextCompat.checkSelfPermission(App.instance, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(p)
            }
        }
        return permissionsNeeded.isEmpty()
    }

    fun setUserNickname(nickname: String) {
        Companion.userNickName.value = nickname
    }
}
