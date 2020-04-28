package com.example.bangkokchallenge.timeline

import android.util.Log
import com.example.bangkokchallenge.data.remote.ApiClient
import com.example.bangkokchallenge.data.remote.ApiService
import com.example.bangkokchallenge.model.TimeLineItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by choejun-yeong on 08/04/2020.
 */

class TimeLineInteractorImpl : TimeLineContract.TimeLineInteractor{

    override fun getTimeLineData(onFinishedListener: TimeLineContract.TimeLineInteractor.OnFinishedListener) {

   //   현재 서버 배포가 되지않은 상황이라 하드 코딩으로 대체함.

//        val service = ApiClient.getClient().create(ApiService::class.java)
//        val call = service.getTimeLineItems("token",0,0) //
//
//        call.enqueue(object : Callback<List<TimeLineItem>> {
//
//            override fun onFailure(call: Call<List<TimeLineItem>>, t: Throwable) {
//                onFinishedListener.onFailure(t)
//            }
//
//            override fun onResponse(call: Call<List<TimeLineItem>>, response: Response<List<TimeLineItem>>) {
//                if(!response.body().isNullOrEmpty()) {
//                    onFinishedListener.onSuccess(response.body()!!)
//                }else {
//                    Log.e("[TimeLineInteractor]","Response Body is Empty")
//                }
//            }
//        })

        onFinishedListener.onTimeLineSuccess(arrayListOf(
            TimeLineItem("1","junyeong","imageUrl","안녕하세요", "2020-04-04",5,false,3),
            TimeLineItem("2","seoungIn","imageUrl","코로나 19 이겨냅시다 화이팅!", "2020-04-04",5,false,3),
            TimeLineItem("3","TaejunP","imageUrl","집콕 챌린지 참여합니다!", "2020-04-03",5,false,3),
            TimeLineItem("4","Hongjae","imageUrl","오늘은 집에서 요리를 만들어 봤어요!", "2020-04-03",5,false,3)
        ))

    }

    override fun putLikeBySelf(position: Int, onFinishedListener: TimeLineContract.TimeLineInteractor.OnFinishedListener) {
        //boolean 내려받을것
        onFinishedListener.onLikeSuccess(position,true)
    }
}