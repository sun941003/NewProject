package com.example.newproject.view.ui.login

import android.content.Intent
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.databinding.ActivityLoginBinding
import com.example.newproject.model.model.User
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.main.MainActivity
import com.example.newproject.view.ui.sign_up.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() , LoginNavigator {
    val vm : LoginViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel? =vm


    override fun init() {
        vm.loginNavigator = this
        //Firebase User를 여기서 세팅하면 에러 발생 -> 널포인트 에러로 나오는거보면 선언이나 사용하는데 순서가 잘못되서 널값이 들어가서 뱉는 에러같음
        //이거 제대로 잡았으니까 UI 랑 dialog 버튼 같은데 색상값도 잡아놔야함
        //지금 현재 디자인 굉장히 개같아 보임 너무 심심해보이거나 조화롭지 않음 -> 이건 수정을 하던지 아니면 통일성을 줘야할듯
        //여기는 로그인, 회원가입 둘다 가능하니까 이쪽에서 작업 계속하면 될듯
    }
    fun gotoSignUp(){
        startActivity(Intent(this,SignUpActivity::class.java))
    }
    fun gotoMain(){
        startActivity(Intent(this,MainActivity::class.java))
    }

    fun autoLogin(){
        mUser = firebaseAuth.currentUser
        //mUser의 위치는 여기 여기에 안들어가도 에러, 다른곳에 들어가도 에러 메세지 뱉는 경우가 있음 어디선가 꼬여서 위치를 변경한것 때문에 오류가 계속 발생했었음
        mUser.getIdToken(true).addOnCompleteListener(this){
            if(it.isSuccessful){
                vm.setToken(it.getResult()!!.token)
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }else{
                showAlertDialog(App.instance.getString(R.string.login_error_message),{
                    finish()
                })
            }
        }
    }

    override fun login() {
        showProgress()
        firebaseAuth.signInWithEmailAndPassword(vm.loginEmail.value,vm.loginPassword.value)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    dismissProgress()
                    autoLogin()
                }else{
                    dismissProgress()
                    showAlertDialog(App.instance.getString(R.string.login_error_message))
                }
            }
    }
}