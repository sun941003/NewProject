package com.example.newproject.view.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.newproject.view.ui.base.BaseViewModel
import java.text.SimpleDateFormat

class MainViewModel : BaseViewModel(){
    val test1 = MutableLiveData("")
    val test2 = MutableLiveData("")
    val test3 = MutableLiveData("")

    val currentTimesCount = MutableLiveData<Int>()

    fun dateCalculator() : Int{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val inputStartDate = "2002-12-07 20:50:00"
        val startDate = dateFormat.parse(inputStartDate)
        val today = System.currentTimeMillis()
        val dDay = ( today - startDate.time )/(60*60*24*1000)
        val currentTimes = dDay/7.0 +1
        //currentTimes가 현재 시간에 해당되는 회차(8시 50분에 변경)
        return currentTimes.toInt()
    }

}