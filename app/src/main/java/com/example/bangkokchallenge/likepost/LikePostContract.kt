package com.example.bangkokchallenge.likepost

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineItem

interface LikePostContract  {
    interface View:BaseView<Presenter>{
        fun setRecyclerViewData(responseData:List<TimeLineItem>)

    }
    interface Presenter:BasePresenter{
        fun requestDataFromServer()
    }

    interface LikePostInteractor{
        fun getLikePostData(onFinishedListener: OnFinishedListener)

        interface OnFinishedListener{
            fun onSuccess(noticeArrayList:List<TimeLineItem>)
            fun onFailure(t: Throwable?)
        }
    }
}