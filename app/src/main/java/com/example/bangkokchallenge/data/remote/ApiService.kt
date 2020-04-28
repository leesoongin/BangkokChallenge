package com.example.bangkokchallenge.data.remote

import com.example.bangkokchallenge.model.AccountDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.request.LoginRequest
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface ApiService {

//    @GET("timeline")
//    fun getTimeLineItems(
//        @Header("Authorization") token: String
//    ): Call<List<TimeLineItem>>

    @POST("account/login")
    fun login(
        @Body key : LoginRequest
    ): Call<AccountDTO>

    @GET("post/list")
    fun getTimeLineItems(): Call<List<TimeLineItem>>

}