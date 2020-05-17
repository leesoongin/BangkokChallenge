package com.example.bangkokchallenge.timeline

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.ResponseModel

/**
 * Created by choejun-yeong on 04/04/2020.
 */


interface TimeLineContract {


    interface View : BaseView<Presenter> {
        fun setRecyclerViewData(responseData : List<TimeLineItem>?)

        fun modifyLikeData(position:Int, boolean: Boolean)

        fun openToCommentPage(discription: String,postId : Int?) //<- comment page로 이동

        fun onResponseFailure(t: Throwable?)
    }


    interface Presenter : BasePresenter, TimeLineItemClickListener {
        fun requestTimeLineDataFromServer(token : String?)
    }


    interface TimeLineInteractor{
        /*like , comment get data*/
        fun getTimeLineData(token : String?,onFinishedListener: OnFinishedListener)

        fun putLikeBySelf(position: Int, onFinishedListener: OnFinishedListener)


         interface OnFinishedListener {
             fun onTimeLineSuccess(noticeArrayList: List<TimeLineItem>?)

             fun onTimeLineFailure(t: Throwable?)

             fun onLikeSuccess(position: Int, isLike: Boolean)

             fun onLikeFailure(t: Throwable?)
        }
    }


    interface TimeLineItemClickListener {
        fun onClickLike(position: Int)

        fun onClickDescription()

        fun onClickComment(discription:String,postId : Int?)
    }
}