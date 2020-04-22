package com.example.bangkokchallenge.timeline

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineItem

/**
 * Created by choejun-yeong on 04/04/2020.
 */


interface TimeLineContract {
    interface View : BaseView<Presenter> {
        /* like, comment, more event listener 이미지까지만 바꿔주고*/
        fun setLikeData()
        fun moveCommentPage()
        // more <- 수정, 삭제 바텀시트로 하자
        fun setRecyclerViewData(responseData : List<TimeLineItem>)
        fun onResponseFailure(t: Throwable?)
    }

    interface Presenter : BasePresenter {
        fun requestTimeLineDataFromServer()

    }

    interface TimeLineInteractor{
        /*like , comment get data*/
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