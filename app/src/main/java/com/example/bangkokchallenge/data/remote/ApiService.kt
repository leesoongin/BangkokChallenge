package com.example.bangkokchallenge.data.remote

import com.example.bangkokchallenge.model.TimeLineItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface ApiService {


    @GET("timeline")
    fun getTimeLineItems(
        @Header("Authorization") token: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<TimeLineItem>>

}