package com.example.newproject.view.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLog
import androidx.core.view.GravityCompat
import com.example.newproject.R
import com.example.newproject.databinding.ActivityMainBinding
import com.example.newproject.model.model.User
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.my_page.MyPageActivity
import com.example.newproject.view.ui.my_page.MyPageViewModel
import com.example.newproject.view.ui.test_activity.TestActivity
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
    뭔가

     */

    override fun init() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.reference

        vm.currentTimesCount.value = vm.dateCalculator()
        //vm.currentTimesCount 나중에 이거 observe해야됨 -> 사용중에 당첨번호가 최신화 되야 되는경우도 있으니까

//        vm.test1.value = mUser.uid
//        vm.test2.value = mUser.email
        mUser = firebaseAuth.currentUser

        reference.child("Users").child(mUser.uid).child("nickname").get().addOnSuccessListener {
            //이렇게 복잡하게 child로 타고 다 들어가야됨
            //db에 데이터 저장 자체가 유저 uid로 저장됨 / 루트 값인 Users값은 내가 회원가입 할때 지정해놓고 저기로 넣어줬음(자동생성)
            //그리고 파이어베이스 회원가입 타면 실시간 디비에 해당 루투안에 uid 자동생성해서 저장됨
            vm.test3.value = it.value.toString()
            //이후 추가적인 데이터베이스에 레코드 추가할땐 구조화 항목에서 처럼 새로 파지말고 파놓고 그걸 갖다 붙이는 형식으로 사용하면 될듯
        }

        reference.child("data").child("1").child("times").get().addOnSuccessListener {
            vm.test1.value = it.value.toString()
        }
    }


    override fun onBackPressed() {
        if (mBinding.dlDrawer.isDrawerOpen(GravityCompat.START)) {
            mBinding.dlDrawer.closeDrawer(GravityCompat.START)
        } else {
            showSelectableDialog(R.string.back_press,{
                super.onBackPressed()
            },{})

        }
    }

    fun gotoMyPage(){
        startActivity(Intent(this,MyPageActivity::class.java))
    }
    fun gotoTest(){
        startActivity(Intent(this,TestActivity::class.java))
    }

    override fun onLeftButtonClick() {
        mBinding.dlDrawer.openDrawer(GravityCompat.START)
    }

    fun testFunction(){

    }


//    override fun onRightButtonClick() {
//        mBinding.dlDrawer.openDrawer(GravityCompat.START)
//    }
}