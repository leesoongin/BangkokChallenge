package com.example.bangkokchallenge.timeline

import android.util.Log
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.data.remote.ApiClient
import com.example.bangkokchallenge.data.remote.ApiService
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse
import com.example.bangkokchallenge.model.response.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by choejun-yeong on 08/04/2020.
 */

class TimeLineInteractorImpl : TimeLineContract.TimeLineInteractor{

    override fun getTimeLineData(token : String?,onFinishedListener: TimeLineContract.TimeLineInteractor.OnFinishedListener) {

        val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.getTimeLineItems("Bearer "+token) //

        call.enqueue(object : Callback<ResponseModel<TimeLineDTO>> {

            override fun onFailure(call: Call<ResponseModel<TimeLineDTO>>, t: Throwable) {
               // onFinishedListener.onFailure(t)
                Log.e("[TimeLineInteractor]","${t.message}")
            }

            override fun onResponse(call: Call<ResponseModel<TimeLineDTO>>, response: Response<ResponseModel<TimeLineDTO>>) {
                response.body()?.let {
                    onFinishedListener.onTimeLineSuccess(response.body()?._embedded?.postList)
                    Log.e("@@time",""+response.body())
                }
            }
        })

    }

    override fun putLikeBySelf(postId : Int?, token : String?,onFinishedListener: TimeLineContract.TimeLineInteractor.OnFinishedListener) {
        //token , postid필요
        val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.setLikeState("Bearer "+token,postId) //

        call.enqueue(object : Callback<LikeResponse> {

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                // onFinishedListener.onFailure(t)
                Log.e("[TimeLineInteractor]","${t.message}")
            }

            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                response.body()?.let {
                    onFinishedListener.onLikeSuccess(response.body()!!)
                    Log.e("@@likeResponsee",""+response.body())
                }
            }
        })


        //onFinishedListener.onLikeSuccess(position,true)
    }
}