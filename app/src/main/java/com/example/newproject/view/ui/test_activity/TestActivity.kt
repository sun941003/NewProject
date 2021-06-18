package com.example.newproject.view.ui.test_activity

import androidx.lifecycle.MutableLiveData
import com.example.newproject.R
import com.example.newproject.databinding.ActivityTestBinding
import com.example.newproject.model.model.LottoOriginalData
import com.example.newproject.model.service.NetworkService
import com.example.newproject.view.ui.base.BaseActivity
import com.example.newproject.view.ui.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class TestActivity : BaseActivity<ActivityTestBinding>() {
    val vm : TestViewModel by viewModel()
    override fun getLayoutId(): Int = R.layout.activity_test

    override fun getViewModel(): BaseViewModel? =vm

    override fun init() {

        //여긴 찐 테스트 환경만 생성
        //db에 데이터를 먼저 넣어야 된다 회차별 데이터?


        vm.timesResult = dateCalculator().toInt()




    }

    fun inputData(){
        //여기서 데이터베이스에 자료 넣어주고 관리해야할듯 -> 이거 하나 짜놓고 나중에 관리자 하나 더 만들어놔야할듯
        //지금 빌드 나누는거 뭔가 이상하게 돌아감 폴더 분리해서 프로젝트 생성하는게 잘 이해안됨
        //https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo
        //url은 이거 써서 불러 오면 되고 이걸 레트로핏으로 받아올지 아닐지는 고민좀 해놔야될듯 숫자값은 No포함되고 0~6까지 있음


    }

    fun loadData(){

    }

    fun createNumber(){
        val lottoNumbers = mutableListOf<Int>()

    }

    fun dateCalculator() : Double{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val inputStartDate = "2002-12-07 20:50:00"
        val startDate = dateFormat.parse(inputStartDate)
        val today = System.currentTimeMillis()
        val dDay = ( today - startDate.time )/(60*60*24*1000)
        val currentTimes = dDay/7.0 +1
        //currentTimes가 현재 시간에 해당되는 회차(8시 50분에 변경)
        return currentTimes
    }


}