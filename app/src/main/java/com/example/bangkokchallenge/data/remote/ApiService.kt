package com.example.bangkokchallenge.data.remote

import com.example.bangkokchallenge.model.AccountDTO
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.request.CommentRequest
import com.example.bangkokchallenge.model.request.LoginRequest
import com.example.bangkokchallenge.model.response.CommentResponse
import com.example.bangkokchallenge.model.response.LikeResponse
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

    /* login */
    @POST("account/login")
    fun login( // login
        @Body key: LoginRequest
    ): Call<AccountDTO>

    /* post list */
    @GET("post")
    fun getTimeLineItems(
        @Header("Authorization") token:String,
        @Query("page") page : Int

    ): Call<ResponseModel<TimeLineDTO>>
    /* post list */
    @GET("post")
    fun getTimeLineItems(
        @Header("Authorization") token:String,
        @Query("hashTag") hashTag : String
    ): Call<ResponseModel<TimeLineDTO>>
    /* post upload */
    @Multipart
    @POST("post/upload")
    fun uploadPost(
        @Header("Authorization") token: String, //bearer :
        @Part("article") article: RequestBody,
        @Part("hashTag") hashTag: RequestBody,
        @Part name: ArrayList<MultipartBody.Part>
    ): Call<UploadResponse>

    @GET("post/{post_id}/comment")
    fun getCommentItem(
        @Header("Authorization") token : String,
        @Path("post_id") postId : Int
    ):Call<List<CommentResponse>>

    @POST("post/{post_id}/comment")
    fun sendCommentItem(
        @Header("Authorization") token : String,
        @Body content : CommentRequest,
        @Path("post_id") postId : Int
    ):Call<CommentResponse>

    @PUT("like/{post_id}")
    fun setLikeState(
        @Header("Authorization") token: String,
        @Path("post_id") postId : Int?
    ):Call<LikeResponse>

    @GET("post/getMyPosts")
    fun getMyPostItem(
        @Header("Authorization") token : String,
        @Query("page") page : Int
    ):Call<ResponseModel<TimeLineDTO>>

    @GET("post/getMyLikes")
    fun getLikePostItem(
        @Header("Authorization") token : String,
        @Query("page") page : Int
    ):Call<ResponseModel<TimeLineDTO>>
}

