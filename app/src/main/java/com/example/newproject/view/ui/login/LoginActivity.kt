package com.example.newproject.view.ui.login

import android.content.Intent
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.databinding.ActivityLoginBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.main.MainActivity
import com.example.newproject.view.ui.sign_up.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() , LoginNavigator {
    val vm : LoginViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel? =vm

    lateinit var mUser : FirebaseUser

    override fun init() {
        vm.loginNavigator = this
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("Users")
        mUser = firebaseAuth.currentUser
    }
    fun gotoSignUp(){
        startActivity(Intent(this,SignUpActivity::class.java))
    }
    fun gotoMain(){
        startActivity(Intent(this,MainActivity::class.java))
    }

    fun autoLogin(){
        mUser.getIdToken(true).addOnCompleteListener(this){
            if(it.isSuccessful){
                vm.setToken(it.getResult()!!.token)
                mUser.displayName
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