package com.example.newproject.view.ui.sign_up

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.util.ValidationCheckUtil
import com.example.newproject.view.ui.base.BaseViewModel
import com.google.android.gms.tasks.OnCompleteListener
import java.util.regex.Pattern

class SignUpViewModel : BaseViewModel() {
    lateinit var signUpNavigator: SignUpNavigator
    val signUpEmail = MutableLiveData("")
    val signUpNickname = MutableLiveData("")
    val signUpPassword = MutableLiveData("")
    val signUpPasswordConfirm = MutableLiveData("")

    val validEmail = MutableLiveData(true)
    val validNickname = MutableLiveData(true)
    val validPassword = MutableLiveData(true)
    val validPasswordConfirm = MutableLiveData(true)

    val emailErrorText = MutableLiveData("")
    val passwordConfirmErrorText = MutableLiveData("")






    fun createUserToFirebase(){
        if (checkInputValidation()) {
            signUpNavigator.registerUser()
        }
    }

    fun emailCheckvalidation() : Boolean{
        val emailPattern : Pattern = android.util.Patterns.EMAIL_ADDRESS
        validEmail.value = emailPattern.matcher(signUpEmail.value).matches()
        emailErrorText.value = App.instance.getString(R.string.email_valid_error)

        return validEmail.value!!
    }

    fun checkInputValidation():Boolean{
        emailCheckvalidation()
        validNickname.value = ValidationCheckUtil.checkInputValidation(signUpNickname.value ?:"",ValidationCheckUtil.NICKNAME_REGEX)

        validPassword.value = ValidationCheckUtil.checkInputValidation(signUpPassword.value ?:"",ValidationCheckUtil.PASSWORD_REGEX)
        validPasswordConfirm.value = signUpPassword.value == signUpPasswordConfirm.value
        if(signUpPasswordConfirm.value.isNullOrBlank()){
            validPasswordConfirm.value=false
            passwordConfirmErrorText.value = App.instance.getString(R.string.password_hint)
        }else{
            if(validPassword.value!!){
                validPasswordConfirm.value = signUpPassword.value == signUpPasswordConfirm.value
                passwordConfirmErrorText.value = App.instance.getString(R.string.password_confirm_error)
            }else{
                validPasswordConfirm.value = ValidationCheckUtil.checkInputValidation(signUpPasswordConfirm.value ?:"",ValidationCheckUtil.PASSWORD_REGEX)
                passwordConfirmErrorText.value = App.instance.getString(R.string.password_hint)
            }
        }
        return validEmail.value!!&&validNickname.value!!&&validPassword.value!!&&validPasswordConfirm.value!!
    }



}