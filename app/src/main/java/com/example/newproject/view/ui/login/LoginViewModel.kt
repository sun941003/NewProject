package com.example.newproject.view.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.util.ValidationCheckUtil
import com.example.newproject.view.ui.base.BaseViewModel
import java.util.regex.Pattern

class LoginViewModel : BaseViewModel() {
    lateinit var loginNavigator: LoginNavigator
    val loginEmail = MutableLiveData("")
    val loginPassword = MutableLiveData("")
    val validEmail = MutableLiveData(true)
    val validPassword = MutableLiveData(true)

    fun registerLogin(){
        if(checkInputValidation()){
            loginNavigator.login()
        }else{
            baseNavigator.showAlertDialog(App.instance.getString(R.string.login_error_message))
        }
    }


    fun emailCheckvalidation() : Boolean{
        val emailPattern : Pattern = android.util.Patterns.EMAIL_ADDRESS
        validEmail.value = emailPattern.matcher(loginEmail.value).matches()

        return validEmail.value!!
    }


    fun checkInputValidation():Boolean{
        emailCheckvalidation()
        validPassword.value = ValidationCheckUtil.checkInputValidation(loginPassword.value ?:"", ValidationCheckUtil.PASSWORD_REGEX)

        return validEmail.value!!&&validPassword.value!!
    }
}