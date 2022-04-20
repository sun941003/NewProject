package com.example.newproject.view.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
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
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val vm: MainViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): BaseViewModel? = vm
    /*
    viewModel에서 firebase쓰기

     */

    override fun init() {



        //firebase setting
        mUser = firebaseAuth.currentUser

        val user = vm.mAuth.currentUser
        vm.mReference.child("Users").child(user.uid).get().addOnSuccessListener {
            vm.setUserNickname(it.child("nickname").value.toString())
        }

//        reference.child("Users").child(mUser.uid).child("nickname").get().addOnSuccessListener {
            //이렇게 복잡하게 child로 타고 다 들어가야됨
            //db에 데이터 저장 자체가 유저 uid로 저장됨 / 루트 값인 Users값은 내가 회원가입 할때 지정해놓고 저기로 넣어줬음(자동생성)
            //그리고 파이어베이스 회원가입 타면 실시간 디비에 해당 루투안에 uid 자동생성해서 저장됨
//            vm.test3.value = it.value.toString()
            //이후 추가적인 데이터베이스에 레코드 추가할땐 구조화 항목에서 처럼 새로 파지말고 파놓고 그걸 갖다 붙이는 형식으로 사용하면 될듯
//        }

        showToast("test -> ${BaseViewModel.currentTimes.value}")
//        showAlertDialog("test --> ${BaseViewModel.jsonData.value!!.get(vm.currentTimesCount).date}")

    }


    fun initItems(){

        reference.child("data").child(vm.currentTimesCount.toString()).get().addOnSuccessListener {
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

    fun testFunction() {

    }

    fun gotoList(){
        startActivity(Intent(this,ListActivity::class.java))
    }



//    override fun onRightButtonClick() {
//        mBinding.dlDrawer.openDrawer(GravityCompat.START)
//    }
}