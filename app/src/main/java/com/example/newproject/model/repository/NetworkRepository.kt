package com.example.newproject.model.repository

import com.example.newproject.model.model.LottoOriginalData
import com.example.newproject.model.repo.ResponseData
import com.example.newproject.model.repo.Result
import com.example.newproject.model.service.NetworkService
import com.example.newproject.util.ConnectionConfigue
import com.example.newproject.util.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository(val service: NetworkService) {


    fun loadData(
        number: Int,
        success: (ResponseData<LottoOriginalData>) -> Unit,
        failed: () -> Unit
    ) {
        service.getData(number).enqueue(object : Callback<ResponseData<LottoOriginalData>> {
            override fun onResponse(
                call: Call<ResponseData<LottoOriginalData>>,
                response: Response<ResponseData<LottoOriginalData>>
            ) {
                response.body()?.let { success(it) }?:failed()
            }

            override fun onFailure(call: Call<ResponseData<LottoOriginalData>>, t: Throwable) {
                failed()
            }
        })
    }

}
