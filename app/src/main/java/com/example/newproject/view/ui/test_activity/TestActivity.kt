package com.example.newproject.view.ui.test_activity

import androidx.lifecycle.MutableLiveData
import com.example.newproject.R
import com.example.newproject.databinding.ActivityTestBinding
import com.example.newproject.model.model.JsonData
import com.example.newproject.model.model.LottoData
import com.example.newproject.model.model.LottoOriginalData
import com.example.newproject.model.service.NetworkService
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TestActivity : BaseActivity<ActivityTestBinding>() {
    val vm : TestViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_test

    override fun getViewModel(): BaseViewModel? =vm

    override fun init() {

        //여긴 찐 테스트 환경만 생성
        //db에 데이터를 먼저 넣어야 된다 회차별 데이터?

        reference = firebaseDatabase.reference.child("data")
        testJsonModel()
//        reference.get().addOnSuccessListener {
//            mBinding.tvTestJsonData.text = "${it.value.toString()}"
//        }


    }
    fun testJsonModel(){

        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                val sampleArray : ArrayList<JsonData>

//                snapshot.getValue<JsonData>() // ArrayList로 안묶임
//                BaseViewModel.jsonData.value = snapshot.getValue<ArrayList<JsonData>>()
                showToast("성공")
                val testValue = ArrayList<JsonData>()
                for (i in 1..10){
                    snapshot.child("$i").getValue<JsonData>()?.let {
                        testValue.add(it)
                        //이렇게 null체크 한번하고 child값으로 불러와야 문제 없이 arrayList로 사용가능할 꺼 같음
                        //다른 애들도 이렇게 하나하나 불러서 list화 했음
                    }
                }
                vm.testArray.value = testValue
                mBinding.tvTestJsonData.text = "${vm.testArray.value.toString()}"
            }

            override fun onCancelled(error: DatabaseError) {
                showToast("실패")
            }

        }
        reference.addValueEventListener(dataListener)
    }
}