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

        fun modifyLikeData(position:Int, boolean: Boolean)

        fun openToCommentPage(discription: String) //<- comment page로 이동

        fun onResponseFailure(t: Throwable?)
    }


    interface Presenter : BasePresenter, TimeLineItemClickListener {
        fun requestTimeLineDataFromServer()
    }


    interface TimeLineInteractor{
        /*like , comment get data*/
        fun getTimeLineData(onFinishedListener: OnFinishedListener)

        fun putLikeBySelf(position: Int, onFinishedListener: OnFinishedListener)


         interface OnFinishedListener {
             fun onTimeLineSuccess(noticeArrayList: List<TimeLineItem>)

             fun onTimeLineFailure(t: Throwable?)

             fun onLikeSuccess(position: Int, isLike: Boolean)

             fun onLikeFailure(t: Throwable?)
        }
    }


    interface TimeLineItemClickListener {
        fun onClickLike(position: Int)

        fun onClickDescription()

        fun onClickComment(discription:String)
    }
}