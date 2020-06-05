package com.example.bangkokchallenge.searchPost

import android.util.Log
import com.example.bangkokchallenge.data.remote.ApiClient
import com.example.bangkokchallenge.data.remote.ApiService
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.response.LikeResponse
import com.example.bangkokchallenge.model.response.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPostInteractorImpl : SearchPostContract.SearchPostInteractor{
    private var pageSize : Int ?=null
    private var pageTotlalElements : Int? = null
    private var pageTotalpages : Int? =null
    private var pageNumber : Int = 0
    private var exTotalPages : Int? =0
    private var exPageNumber : Int? =0

    private var flag : Boolean  =true

    override fun getSearchPostData(token : String?,hashTag : String?,onFinishedListener: SearchPostContract.SearchPostInteractor.OnFinishedListener) {

        val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.getTimeLineItems("Bearer "+token,hashTag.toString()) //

        if(exTotalPages!! > pageNumber){
            flag = true
        }
        if(flag){ //true이면 호출 아니면 호출안함
            flag =false //처음엔 true -> 한번 호출 후 false 로 변환 그리고 다음 호출부터 상태 변환되면 실행되게끔
            Log.d("adad",token)
            call.enqueue(object : Callback<ResponseModel<TimeLineDTO>> {

                override fun onFailure(call: Call<ResponseModel<TimeLineDTO>>, t: Throwable) {
                    // onFinishedListener.onFailure(t)
                    Log.e("[TimeLineInteractor]","${t.message}")
                }

                override fun onResponse(call: Call<ResponseModel<TimeLineDTO>>, response: Response<ResponseModel<TimeLineDTO>>) {
                    response.body()?.let {
                        Log.d("totalpost",""+response.body()?.page?.totalElements)
                        onFinishedListener.onSearchPostSuccess(response.body()?._embedded?.postList)

                        pageTotalpages=response.body()?.page?.totalPages
                        exTotalPages=pageTotalpages
                        if(pageTotalpages!! > pageNumber){
                            pageNumber=response.body()?.page!!.number+1
                        }

                        Log.e("@@time",""+response.body())
                    }
                }
            })//call
        }//if


    }

    override fun putLikeBySelf(postId : Int?, token : String?,onFinishedListener: SearchPostContract.SearchPostInteractor.OnFinishedListener) {
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