package com.example.bangkokchallenge.mypage

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineItem

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface MyPageContract {
    interface View:BaseView<Presenter>{
        fun setRecyclerViewData(responseData:List<TimeLineItem>) //recyclerView 에 data setting
        fun onResponseFailure(t: Throwable?)
    }

    interface Presenter:BasePresenter{ //서버로부터 데이터 전송 요청
        fun requestMyPageDataFromSever()
    }

    interface MyPageInteractor{ // presenter에서 데이터 전송 요청시  받아오기
        fun getMyPageData(token : String,onFinishedListener: OnFinishedListener)

        interface OnFinishedListener{
            fun onSuccess(noticeArrayList:List<TimeLineItem>) //
            fun onFailure(t: Throwable?)
        }

    }
}