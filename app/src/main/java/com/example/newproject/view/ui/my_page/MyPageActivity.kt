package com.example.newproject.view.ui.my_page

import android.content.Intent
import com.example.newproject.R
import com.example.newproject.databinding.ActivityMyPageBinding
import com.example.newproject.model.enums.PasswordFindEnums
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.example.newproject.view.ui.update_password.UpdatePasswordAcitivty
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyPageActivity : BaseActivity<ActivityMyPageBinding>() {
    val vm : MyPageViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_my_page

    override fun getViewModel(): BaseViewModel? = vm

    override fun init() {


        
    }
    fun gotoUpdatePassword(){
        startActivity(Intent(this,UpdatePasswordAcitivty::class.java).apply {
            putExtra(UpdatePasswordAcitivty.INTENT_UPDATE_TYPE,PasswordFindEnums.UPDATE)
        })
    }
}