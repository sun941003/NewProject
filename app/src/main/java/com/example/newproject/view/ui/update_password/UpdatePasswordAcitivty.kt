package com.example.newproject.view.ui.update_password

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.newproject.R
import com.example.newproject.databinding.ActivityUpdatePasswordBinding
import com.example.newproject.model.enums.PasswordFindEnums
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdatePasswordAcitivty : BaseActivity<ActivityUpdatePasswordBinding>() {
    val vm: UpdatePasswordViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_update_password

    override fun getViewModel(): BaseViewModel? = vm

    companion object {
        val INTENT_UPDATE_TYPE = "selectedUpdateType"
    }

    override fun init() {
        vm.type.value =
            (intent.getSerializableExtra(INTENT_UPDATE_TYPE) as? PasswordFindEnums) ?: PasswordFindEnums.UPDATE
        //실수로 라도 타입 안보내도 오류 나지 않게 nullSafety처리 해놨음

        resetErrorStatus(mBinding.tvNewPassword,vm.validPassword)
        resetErrorStatus(mBinding.tvNewConfirmPassword,vm.validConfirmPassword)
    }

    fun sendEmail() {
        val email = firebaseAuth.currentUser.email
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("비밀번호 변경 이메일이 발송되었습니다.")
                }
            }
    }

    fun changedPassword() {
        val signedUser = firebaseAuth.currentUser
        val password = vm.newPassword.value!!
        if (vm.checkValidation()) {
            signedUser!!.updatePassword(password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("비밀번호가 성공적으로 변경 되었습니다.")
                    }else{
                        showAlertDialog("네트워크 오류가 발생했습니다.\n다시 로그인 후 진행해주세요.",{
                            logOut()
                        })
                    }
                }

        }
    }

    fun resetErrorStatus(editText: EditText, isValid: MutableLiveData<Boolean>) {
        editText.addTextChangedListener {
            isValid.value = true
        }
    }

}