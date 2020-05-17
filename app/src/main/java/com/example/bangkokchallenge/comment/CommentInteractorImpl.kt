package com.example.bangkokchallenge.comment

import android.util.Log
import com.example.bangkokchallenge.data.remote.ApiClient
import com.example.bangkokchallenge.data.remote.ApiService
import com.example.bangkokchallenge.model.request.CommentRequest
import com.example.bangkokchallenge.model.response.CommentResponse
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentInteractorImpl :CommentContract.CommentInteractor{

    override fun getCommentData(onFinishedListener: CommentContract.CommentInteractor.OnFinishedListener,token : String?) {

        val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.getCommentItem("Bearer "+token)

        call.enqueue(object : Callback<List<CommentResponse>> {

            override fun onFailure(call: Call<List<CommentResponse>>, t: Throwable) {
                // onFinishedListener.onFailure(t)
                Log.e("[TimeLineInteractor]","${t.message}")
            }

            override fun onResponse(call: Call<List<CommentResponse>>, response: Response<List<CommentResponse>>) {
                response.body()?.let {
                    onFinishedListener.getCommentDataSuccess(response.body()!!)
                    Log.e("@@commentList",""+response.body())
                }
            }
        })
    }

    override fun sendCommentData(onFinishedListener: CommentContract.CommentInteractor.OnFinishedListener, token: String?) {
        val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.sendCommentItem("Bearer "+token, CommentRequest("test1"))

        call.enqueue(object : Callback<CommentResponse> {

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                // onFinishedListener.onFailure(t)
                Log.e("[TimeLineInteractor]","${t.message}")
            }

            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                response.body()?.let {
                    onFinishedListener.sendCommentDataSuccess(response.body()!!)
                    Log.e("@@commentUpload",""+response.body())
                }
            }
        })
    }



}