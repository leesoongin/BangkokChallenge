package com.example.bangkokchallenge.mypage

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface MyPageContract {
    interface View:BaseView<Presenter>{
        fun setRecyclerViewData(responseData:List<TimeLineItem>?) //recyclerView 에 data setting

        fun modifyLikeData(likeResponse: LikeResponse)

        fun openToCommentPage(discription: String,hashTag : String, postId : Int?) //<- comment page로 이동

        fun onResponseFailure(t: Throwable?)
    }

    interface Presenter:BasePresenter, MyPageItemClickListener{ //서버로부터 데이터 전송 요청
        fun requestMyPageDataFromSever(token:String?)
    }

    interface MyPageInteractor{ // presenter에서 데이터 전송 요청시  받아오기
        fun getMyPageData(token : String?,onFinishedListener: OnFinishedListener)

        fun putLikeBySelf(postId: Int?, token : String? ,onFinishedListener: OnFinishedListener)

        interface OnFinishedListener{
            fun onMyPageSuccess(noticeArrayList:List<TimeLineItem>?) //

            fun onLikeSuccess(likeResponse: LikeResponse)

            fun onFailure(t: Throwable?)
        }

    }


    interface MyPageItemClickListener {
        fun onClickLike(postId: Int?,token : String?)

        fun onClickDescription()

        fun onClickComment(discription:String,hashTag : String,postId : Int?)
    }
}