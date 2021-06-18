package com.example.newproject.view.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.databinding.ActivitySplashBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.login.LoginActivity
import com.example.newproject.view.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>(),SplashNavigator {
    val vm : SplashViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getViewModel(): BaseViewModel?=vm

    override fun init() {
        vm.splashNavigator = this

        //이게 푸시 알람 처럼 스플레시 화면 한번 거치는 화면에서 스택 관련해서 오류 생기면 한번 초기화 시키는 코드
//        App.activities.forEach{it.finish()}
//        App.activities.clear()
//        val connectedRef = firebaseDatabase.getReference(".info/connected")
//        connectedRef.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val connected = snapshot.getValue(Boolean::class.java) ?: false
//                if(connected){
//                    Handler(Looper.getMainLooper()).postDelayed({vm.initApp()},1000)
//                }else{
//                    showAlertDialog(App.instance.getString(R.string.failed_connection),{finish()})
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                showAlertDialog(error.toString(),{finish()})
//            }
//            //여기서 통신 확인할 수 있음 네트워크 연결이 안되있으면 더이상 진행 불가 (애드몹이 들어간다면 네트워크가 중요할꺼 같아서)
//        })

        Handler(Looper.getMainLooper()).postDelayed({vm.initApp()},1000)

    }

    override fun gotoMain() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun gotoLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    fun checkConnectingDatabase() : String{
        val presenceRef = firebaseDatabase.getReference("disconnectmessage")
        presenceRef.onDisconnect().setValue("I disconnected")

        return "I disconnected"
    }
}