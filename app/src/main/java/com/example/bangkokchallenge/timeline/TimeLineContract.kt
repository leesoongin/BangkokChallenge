package com.example.bangkokchallenge.timeline

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineItem

/**
 * Created by choejun-yeong on 04/04/2020.
 */


interface TimeLineContract {
    interface View : BaseView<Presenter> {
        fun setRecyclerViewData(responseData : List<TimeLineItem>)
        fun onResponseFailure(t: Throwable?)
    }

    interface Presenter : BasePresenter {
        fun requestTimeLineDataFromServer()
    }

    interface TimeLineInteractor{
        fun getTimeLineData(onFinishedListener: OnFinishedListener)

         interface OnFinishedListener {
            fun onSuccess(noticeArrayList: List<TimeLineItem>)
            fun onFailure(t: Throwable?)
        }
    }

    interface TimeLineItemClickListener {
        fun onClickLike()
        fun onClickDescription()
        fun onClickComment()
    }
}