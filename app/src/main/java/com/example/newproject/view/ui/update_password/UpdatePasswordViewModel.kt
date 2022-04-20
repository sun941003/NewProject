package com.example.newproject.view.ui.update_password

import androidx.lifecycle.MutableLiveData
import com.example.newproject.model.enums.PasswordFindEnums
import com.example.newproject.util.ValidationCheckUtil
import com.example.newproject.view.ui.base.BaseViewModel

class UpdatePasswordViewModel : BaseViewModel() {
    val type = MutableLiveData<PasswordFindEnums>()

    val newPassword = MutableLiveData<String>()
    val newConfirmPassword = MutableLiveData<String>()
    val validPassword = MutableLiveData(true)
    val validConfirmPassword = MutableLiveData(true)

    fun checkValidation() : Boolean{
        validPassword.value = ValidationCheckUtil.checkInputValidation(newPassword.value?:"",ValidationCheckUtil.PASSWORD_REGEX)
        if(validPassword.value!!){
            validConfirmPassword.value = newPassword.value == newConfirmPassword.value
        }else{
            validConfirmPassword.value = validPassword.value
        }
        return validPassword.value!!&&validConfirmPassword.value!!
    }
}