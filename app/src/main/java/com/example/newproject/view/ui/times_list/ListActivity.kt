package com.example.newproject.view.ui.times_list

import com.example.newproject.R
import com.example.newproject.databinding.ActivityListBinding
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : BaseActivity<ActivityListBinding>() {
    val vm : ListViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_list

    override fun getViewModel(): BaseViewModel? =vm

    override fun init() {
        mData = FirebaseDatabase.getInstance().reference.child("data")
        mData.keepSynced(true) // 얘가 데이터를 로컬에서 사용하게 지정 해놈
        mData.child(BaseViewModel.currentTimes.value.toString()).get().addOnSuccessListener {
            showAlertDialog("${it.child("no1").value.toString()}")
        }
        vm.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mData.keepSynced(false)
    }
}