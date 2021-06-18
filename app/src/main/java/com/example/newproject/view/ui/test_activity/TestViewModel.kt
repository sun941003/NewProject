package com.example.newproject.view.ui.test_activity

import androidx.lifecycle.MutableLiveData
import com.example.newproject.model.model.LottoOriginalData
import com.example.newproject.model.model.NetworkApiResponse
import com.example.newproject.model.repo.Result
import com.example.newproject.model.repository.NetworkRepository
import com.example.newproject.model.service.NetworkService
import com.example.newproject.view.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestViewModel(val networkRepository: NetworkRepository) : BaseViewModel() {
    var timesResult = 1

    val testData = MutableLiveData<LottoOriginalData>()


}