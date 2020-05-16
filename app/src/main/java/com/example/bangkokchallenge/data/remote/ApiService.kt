package com.example.bangkokchallenge.data.remote

import com.example.bangkokchallenge.model.AccountDTO
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.request.LoginRequest
import com.example.bangkokchallenge.model.response.ResponseModel
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

    @POST("account/login")
    fun login(
        @Body key: LoginRequest
    ): Call<AccountDTO>


    @GET("post")
    fun getTimeLineItems(
        @Header("Authorization") token:String
    ): Call<ResponseModel<TimeLineDTO>>

    @Multipart
    @POST("post/upload")
    fun uploadPost(
        @Header("Authorization") token: String, //bearer :
        @Part("article") article: RequestBody,
        @Part("hashTag") hashTag: RequestBody,
        @Part name: MultipartBody.Part
    ): Call<UploadResponse>

}