package com.example.newproject.view.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import android.util.Log
import androidx.core.view.GravityCompat
import com.example.newproject.R
import com.example.newproject.databinding.ActivityMainBinding
import com.example.newproject.model.model.JsonData
import com.example.newproject.model.model.LottoData
import com.example.newproject.model.model.User
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.my_page.MyPageActivity
import com.example.newproject.view.ui.my_page.MyPageViewModel
import com.example.newproject.view.ui.test_activity.TestActivity
import com.example.newproject.view.ui.times_list.ListActivity
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.http.POST
import java.lang.RuntimeException
import java.time.Duration
import java.time.LocalDateTime
import java.time.Period
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val vm: MainViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): BaseViewModel? = vm

    override fun init() {



        //firebase setting
        mUser = firebaseAuth.currentUser


        val user = vm.mAuth.currentUser
        vm.mReference.child("Users").child(user.uid).get().addOnSuccessListener {
            vm.setUserNickname(it.child("nickname").value.toString())
        }
        initItems()

        val current = LocalDateTime.now()
        val secondsDay = (60*60*24).toLong()*7

        val seconds = Duration.between(start,current).seconds

        val result = seconds/secondsDay +1
        showToast(result.toString())



    }


    fun initItems(){
        reference.child("data").child(vm.currentTimesCount.toString()).get().addOnSuccessListener {
            val localList = ArrayList<LottoData>()
            vm.currentTimesNumbers.value = LottoData(
                it.child("1st_count").value.toString(),
                it.child("1st_money").value.toString(),
                it.child("2nd_count").value.toString(),
                it.child("2nd_money").value.toString(),
                it.child("3th_count").value.toString(),
                it.child("3th_money").value.toString(),
                it.child("4th_count").value.toString(),
                it.child("4th_money").value.toString(),
                it.child("5th_count").value.toString(),
                it.child("5th_money").value.toString(),
                it.child("date").value.toString(),
                it.child("no1").value.toString(),
                it.child("no2").value.toString(),
                it.child("no3").value.toString(),
                it.child("no4").value.toString(),
                it.child("no5").value.toString(),
                it.child("no6").value.toString(),
                it.child("no7").value.toString(),
                it.child("times").value.toString()
            )
            Log.e("mainLotto","initItems = ${vm.currentTimesNumbers.value}")
        }

    }


    override fun onBackPressed() {
        if (mBinding.dlDrawer.isDrawerOpen(GravityCompat.START)) {
            mBinding.dlDrawer.closeDrawer(GravityCompat.START)
        } else {
            showSelectableDialog(R.string.back_press, {
                super.onBackPressed()
            }, {})

        }
    }

    fun gotoMyPage() {
        startActivity(Intent(this, MyPageActivity::class.java))
    }

    fun gotoTest() {
        startActivity(Intent(this, TestActivity::class.java))
    }

    override fun onLeftButtonClick() {
        mBinding.dlDrawer.openDrawer(GravityCompat.START)
    }

    fun gotoList(){
        startActivity(Intent(this,ListActivity::class.java))
    }
}