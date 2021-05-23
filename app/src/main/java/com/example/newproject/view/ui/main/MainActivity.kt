package com.example.newproject.view.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import androidx.core.view.GravityCompat
import com.example.newproject.R
import com.example.newproject.databinding.ActivityMainBinding
import com.example.newproject.model.model.User
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.http.POST
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val vm: MainViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): BaseViewModel? = vm
    /*
    까먹기 전에 미리 정리해놓기
    로그인, 회원가입, 토큰까진 됨
    -> db에서 유저 정보 받아오는 부분 남음 -> firebase realtimedatabase로 되있을꺼임

     */

    override fun init() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.reference









    }




    override fun onBackPressed() {
        if (mBinding.dlDrawer.isDrawerOpen(GravityCompat.START)) {
            mBinding.dlDrawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onLeftButtonClick() {
        mBinding.dlDrawer.openDrawer(GravityCompat.START)
    }

    fun loadUserInfo(){
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference()

        reference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

//    override fun onRightButtonClick() {
//        mBinding.dlDrawer.openDrawer(GravityCompat.START)
//    }
}