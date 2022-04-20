package com.example.newproject.view.ui.times_list

import androidx.lifecycle.MutableLiveData
import com.example.newproject.model.model.LottoData
import com.example.newproject.view.ui.base.BaseViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class ListViewModel : BaseViewModel() {

    val numTest = MutableLiveData<LottoData>()
    val allData = MutableLiveData<ArrayList<LottoData>>()
    lateinit var testData : DatabaseReference

    fun loadData(){
        val firebaseDatabase = FirebaseDatabase.getInstance()
        testData = firebaseDatabase.reference.child("data")
        loadCurrentData()
        loadAllData()
    }

    fun loadCurrentData(){
        testData.child(currentTimes.value.toString()).get().addOnSuccessListener {
            numTest.value = LottoData(
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

    fun loadAllData(){
        val instanceData = ArrayList<LottoData>().apply {
            for (i in 1..currentTimes.value!!-1){
                testData.child(i.toString()).get().addOnSuccessListener {
                    this.add( LottoData(
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
                    )
                }
            }
        }
        allData.value = instanceData
    }

}