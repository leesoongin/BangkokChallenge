package com.example.bangkokchallenge.searchPost

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse

interface SearchPostContract {


    interface View : BaseView<Presenter> {
        fun setRecyclerViewData(responseData : List<TimeLineItem>?)

        fun modifyLikeData(likeResponse: LikeResponse)

        fun openToCommentPage(discription: String,hashTag: String,postId : Int?) //<- comment page로 이동
    }


    interface Presenter : BasePresenter, SearchPostItemClickListener {
        fun requestSearchPostDataFromServer(token : String?,hashTag : String?)
    }


    interface SearchPostInteractor{
        /*like , comment get data*/
        fun getSearchPostData(token : String?,hashTag: String?,onFinishedListener: OnFinishedListener)

        fun putLikeBySelf(postId: Int?, token : String? ,onFinishedListener: OnFinishedListener)


        interface OnFinishedListener {
            fun onSearchPostSuccess(noticeArrayList: List<TimeLineItem>?)

            fun onSearchPostFailure(t: Throwable?)

            fun onLikeSuccess(likeResponse: LikeResponse)

            fun onLikeFailure(t: Throwable?)
        }
    }


    interface SearchPostItemClickListener {
        fun onClickLike(postId: Int?,token : String?)

        fun onClickDescription()

        fun onClickComment(discription:String,hashTag : String,postId : Int?)
    }
}