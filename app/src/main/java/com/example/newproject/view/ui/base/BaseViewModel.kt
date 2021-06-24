package com.example.newproject.view.ui.base

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newproject.App
import com.example.newproject.model.model.JsonData
import com.example.newproject.model.model.User
import com.example.newproject.util.SharedPreferenceUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

open class BaseViewModel : ViewModel() {
    companion object {
        val token = MutableLiveData<String?>(SharedPreferenceUtil.getToken())
        val userInfo = MutableLiveData<User>()
        val userNickName = MutableLiveData("")
        val currentTimes = MutableLiveData(0)
        val jsonData = MutableLiveData<ArrayList<JsonData>>()
    }
    lateinit var baseNavigator: BaseNavigator

    //일단 이렇게 잡아놓기
    var mAuth = FirebaseAuth.getInstance()
    var mDatabase = FirebaseDatabase.getInstance()
    var mReference = FirebaseDatabase.getInstance().reference

    fun setUserInfo(user : User){
        Companion.userInfo.value = user
    }


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
