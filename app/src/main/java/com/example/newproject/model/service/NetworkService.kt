package com.example.newproject.model.service

import com.example.newproject.model.model.LottoOriginalData
import com.example.newproject.model.model.User
import com.example.newproject.model.repo.ResponseData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("/common.do=getLottoNumber&drwNo={number}")
    fun getData(
        @Path("number") number : Int
    ) : Call<ResponseData<LottoOriginalData>>
}