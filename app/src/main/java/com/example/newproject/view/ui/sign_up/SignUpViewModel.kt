package com.example.newproject.view.ui.sign_up

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import com.example.newproject.util.ValidationCheckUtil
import com.example.newproject.view.ui.base.BaseViewModel
import java.util.regex.Pattern

class SignUpViewModel : BaseViewModel() {
    val signUpEmail = MutableLiveData("")
    val signUpPassword = MutableLiveData("")
    val signUpPasswordConfirm = MutableLiveData("")

    val validEmail = MutableLiveData(true)
    val validPassword = MutableLiveData(true)
    val validPasswordConfirm = MutableLiveData(true)





    fun createUserToFirebase(){
        firebaseAuth.createUserWithEmailAndPassword(signUpEmail.value,signUpPassword.value)
    }

    fun emailCheck(){
        val emailPattern : Pattern = android.util.Patterns.EMAIL_ADDRESS
        if(emailPattern.matcher(signUpEmail.value).matches()){
            baseNavigator.showAlertDialog("co.kr들감")
        }else{
            baseNavigator.showAlertDialog("안들감")
        }
    }

    fun checkInputValidation():Boolean{
        val emailPattern : Pattern = android.util.Patterns.EMAIL_ADDRESS
        validEmail.value = emailPattern.matcher(signUpEmail.value).matches()

//        validPassword.value = ValidationCheckUtil.checkInputValidation(signUpPassword.value ?:"",ValidationCheckUtil.PASSWORD_REGEX)
//        validEmail.value = ValidationCheckUtil.checkInputValidation(signUpPasswordConfirm.value ?:"",ValidationCheckUtil.PASSWORD_REGEX)

//        return validEmail.value!!&&validPassword.value!!&&validPasswordConfirm.value!!
        return validEmail.value!!
    }




}