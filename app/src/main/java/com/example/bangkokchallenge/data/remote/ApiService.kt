package com.example.bangkokchallenge.data.remote

import com.example.bangkokchallenge.model.AccountDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.request.LoginRequest
import com.example.bangkokchallenge.model.response.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by choejun-yeong on 04/04/2020.
 */
// 서버에 요청할 .. api interface

interface ApiService {

//    @GET("timeline")
//    fun getTimeLineItems(
//        @Header("Authorization") token: String

    //    ): Call<List<TimeLineItem>>
    @POST("account/login")
    fun login(
        @Body key: LoginRequest
    ): Call<AccountDTO>

    @GET("post/list")
    fun getTimeLineItems(
        @Header("Authorization") token:String
    ): Call<List<TimeLineItem>>

    @Multipart
@POST("post/upload")
fun uploadPost(
    @Header("Authorization") token: String,
    @Part("article") article: RequestBody,
    @Part("hashTag") hashTag: RequestBody,
    @Part("file") image: MultipartBody.Part
): Call<UploadResponse>
}