package com.example.newproject.view.ui.sign_up

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.databinding.ActivitySignUpBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(),SignUpNavigator {
    val vm :SignUpViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_sign_up

    override fun getViewModel(): BaseViewModel? =vm

    override fun init() {
        vm.signUpNavigator = this
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("Users")

        resetErrorStatus(mBinding.tvSignUpEmail,vm.validEmail)
        resetErrorStatus(mBinding.tvSignUpNickname,vm.validNickname)
        resetErrorStatus(mBinding.tvSignUpPassword,vm.validPassword)
        resetErrorStatus(mBinding.tvSignUpPasswordConfirm,vm.validPasswordConfirm)

    }

    fun resetErrorStatus(editText: EditText, isValid: MutableLiveData<Boolean>) {
        editText.addTextChangedListener {
            isValid.value = true
        }
    }

    override fun registerUser() {
        if(vm.checkInputValidation()) {
            showProgress()
            firebaseAuth.createUserWithEmailAndPassword(vm.signUpEmail.value, vm.signUpPassword.value)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        dismissProgress()
                        val userInfo = firebaseAuth.currentUser
                        val email = userInfo.email
                        val uid = userInfo.uid
                        val nickname = vm.signUpNickname.value!!
                        val hashMap: HashMap<Any, String> = HashMap()
                        hashMap["uid"] = uid
                        hashMap["email"] = email
                        hashMap["nickname"] = nickname
                        reference.child(uid).setValue(hashMap)
                        showToast(R.string.sign_up_success)
                        finish()

                    } else {
                        dismissProgress()
                        vm.validEmail.value = false
                        vm.emailErrorText.value = App.instance.getString(R.string.sign_up_email_error)
                    }
                }
        }else{
            showToast(App.instance.getString(R.string.sign_up_error))
        }
    }
}