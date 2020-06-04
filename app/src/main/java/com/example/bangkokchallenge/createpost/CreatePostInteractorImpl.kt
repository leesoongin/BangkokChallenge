package com.example.bangkokchallenge.createpost

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.data.remote.ApiClient
import com.example.bangkokchallenge.data.remote.ApiService
import com.example.bangkokchallenge.model.CreatePostDTO
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.response.ResponseModel
import com.example.bangkokchallenge.model.response.UploadResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.create
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception


class CreatePostInteractorImpl : CreatePostContract.CreatePostInteractor{

    override fun sendPost(onFinishedListener: CreatePostContract.CreatePostInteractor.OnFinishedListener,createPostDTO: CreatePostDTO) {

        val file = File(createPostDTO.uriList?.get(0)?.path)
        Log.d("@@file","${file}")
        val surveyBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val article = RequestBody.create("text/plain".toMediaTypeOrNull(),createPostDTO.article.toString())
        val hashTag = RequestBody.create("text/plain".toMediaTypeOrNull(),createPostDTO.hashTag.toString())

        Log.d("@@hashTag","${hashTag.toString()}")

        val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.uploadPost("Bearer "+createPostDTO.token,article,hashTag,MultipartBody.Part.createFormData("fileList",file.name,surveyBody)) //

        call.enqueue(object : Callback<UploadResponse> {

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                // onFinishedListener.onFailure(t)
                Log.e("[TimeLineInteractor]","${t.message}")
            }

            override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                response.body()?.let {

                    onFinishedListener.OnSendPostSuccess()
                    Log.e("@@res",""+response.body())
                }
            }
        })
    }//sendPost
}