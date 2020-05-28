package com.example.bangkokchallenge.timeline

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse
import com.example.bangkokchallenge.model.response.ResponseModel

/**
 * Created by choejun-yeong on 04/04/2020.
 */


interface TimeLineContract {


    interface View : BaseView<Presenter> {
        fun setRecyclerViewData(responseData : List<TimeLineItem>?)

        fun modifyLikeData(likeResponse: LikeResponse)

        fun openToCommentPage(discription: String,hashTag: String,postId : Int?) //<- comment page로 이동

        fun onResponseFailure(t: Throwable?)
    }


    interface Presenter : BasePresenter, TimeLineItemClickListener {
        fun requestTimeLineDataFromServer(token : String?)
    }


    interface TimeLineInteractor{
        /*like , comment get data*/
        fun getTimeLineData(token : String?,onFinishedListener: OnFinishedListener)

        fun putLikeBySelf(postId: Int?, token : String? ,onFinishedListener: OnFinishedListener)


         interface OnFinishedListener {
             fun onTimeLineSuccess(noticeArrayList: List<TimeLineItem>?)

             fun onTimeLineFailure(t: Throwable?)

             fun onLikeSuccess(likeResponse: LikeResponse)

             fun onLikeFailure(t: Throwable?)
        }
    }


    interface TimeLineItemClickListener {
        fun onClickLike(postId: Int?,token : String?)

        fun onClickDescription()

        fun onClickComment(discription:String,hashTag : String,postId : Int?)
    }
}