package com.example.bangkokchallenge.likepost

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse
import com.example.bangkokchallenge.mypage.MyPageContract

interface LikePostContract  {
    interface View:BaseView<Presenter>{
        fun setRecyclerViewData(responseData:List<TimeLineItem>?)

        fun openToCommentPage(discription: String,postId : Int?) //<- comment page로 이동

        fun modifyLikeData(likeResponse: LikeResponse)
    }
    interface Presenter:BasePresenter,LikePostItemClickListener{
        fun requestLikePostDataFromServer(token : String?)
    }

    interface LikePostInteractor{
        fun getLikePostData(token : String? ,onFinishedListener: OnFinishedListener)

        fun putLikeBySelf(postId: Int?, token : String? ,onFinishedListener: OnFinishedListener)

        interface OnFinishedListener{
            fun onLikePostSuccess(noticeArrayList:List<TimeLineItem>?)

            fun onLikeSuccess(likeResponse: LikeResponse)

            fun onFailure(t: Throwable?)
        }
    }

    interface LikePostItemClickListener {
        fun onClickLike(postId: Int?,token : String?)

        fun onClickDescription()

        fun onClickComment(discription:String,postId : Int?)
    }

}