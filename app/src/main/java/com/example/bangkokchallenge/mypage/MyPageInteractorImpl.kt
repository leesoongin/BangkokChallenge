package com.example.bangkokchallenge.mypage

import android.util.Log
import com.example.bangkokchallenge.data.remote.ApiClient
import com.example.bangkokchallenge.data.remote.ApiService
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageInteractorImpl : MyPageContract.MyPageInteractor{
    override fun getMyPageData(token : String,onFinishedListener: MyPageContract.MyPageInteractor.OnFinishedListener) {
      /*  val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.getTimeLineItems("Bearer "+token) //

        Log.d("adad",token)
        call.enqueue(object : Callback<ResponseModel<TimeLineDTO>> {

            override fun onFailure(call: Call<ResponseModel<TimeLineDTO>>, t: Throwable) {
                // onFinishedListener.onFailure(t)
                Log.e("[TimeLineInteractor]","${t.message}")
            }

            override fun onResponse(call: Call<ResponseModel<TimeLineDTO>>, response: Response<ResponseModel<TimeLineDTO>>) {
                response.body()?.let {

                    onFinishedListener.onSuccess(response.body()?._embedded?.postList)

                    pageTotalpages=response.body()?.page?.totalPages
                    if(pageTotalpages!! > pageNumber){
                        pageNumber=response.body()?.page!!.number+1
                    }


                    Log.e("@@time",""+response.body())
                }
            }
        })*/
    }


}