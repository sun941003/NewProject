package com.example.newproject.view.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.example.newproject.R
import com.example.newproject.databinding.ActivityMainBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    val vm: MainViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): BaseViewModel? = vm

    override fun init() {

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

//    override fun onRightButtonClick() {
//        mBinding.dlDrawer.openDrawer(GravityCompat.START)
//    }
}