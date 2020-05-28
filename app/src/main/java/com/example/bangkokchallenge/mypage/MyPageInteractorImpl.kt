package com.example.bangkokchallenge.mypage

import android.util.Log
import com.example.bangkokchallenge.data.remote.ApiClient
import com.example.bangkokchallenge.data.remote.ApiService
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse
import com.example.bangkokchallenge.model.response.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageInteractorImpl : MyPageContract.MyPageInteractor {

    private var pageSize: Int? = null
    private var pageTotalElements: Int? = null
    private var pageTotalpages: Int? = null
    private var pageNumber: Int = 0

    override fun getMyPageData(token: String?, onFinishedListener: MyPageContract.MyPageInteractor.OnFinishedListener) {

        val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.getMyPostItem("Bearer " + token, pageNumber) //

        Log.d("adad", token)
        call.enqueue(object : Callback<ResponseModel<TimeLineDTO>> {

            override fun onFailure(call: Call<ResponseModel<TimeLineDTO>>, t: Throwable) {
                Log.e("[MyPageInteractor]", "${t.message}")
            }

            override fun onResponse(call: Call<ResponseModel<TimeLineDTO>>, response: Response<ResponseModel<TimeLineDTO>>) {
                response.body()?.let {
                    onFinishedListener.onMyPageSuccess(response.body()?._embedded?.postList)

                    pageSize = response.body()?.page?.size // 한번에 넘어오는 page size
                    pageTotalElements = response.body()?.page?.totalElements //내가 올린 게시물의 총 개수

                    pageTotalpages = response.body()?.page?.totalPages //총 페이지의 개수
                    if (pageTotalpages!! > pageNumber) {
                        pageNumber = response.body()?.page!!.number + 1 // 현재 페이지의 number
                    }//inner if

                    Log.e("@@time", "" + response.body())
                }//let
            }//onresponse
        })
    }//getMyPageData

    override fun putLikeBySelf(
        postId: Int?,
        token: String?,
        onFinishedListener: MyPageContract.MyPageInteractor.OnFinishedListener) {
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


}//class