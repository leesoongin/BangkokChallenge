package com.example.bangkokchallenge.comment

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.response.CommentResponse

interface CommentContract {
    interface View:BaseView<Presenter>{
        fun setRecyclerViewData(responseData :List<CommentResponse>) //response 받아온 comment data adapter 에 저장

        fun closeCommentPage()
    }

    interface Presenter:BasePresenter{
        fun requestCommentDataFromServer(token : String?,postId : Int)

        fun requestSendCommentDataToServer(token : String?,comment : String?,postId : Int)

        fun requestCloseCommentPage()
    }

    interface CommentInteractor{
        fun getCommentData(onFinishedListener:OnFinishedListener, token : String?,postId : Int)

        fun sendCommentData(onFinishedListener:OnFinishedListener, token: String?,comment:String?,postId: Int)

        interface OnFinishedListener{
            fun getCommentDataSuccess(noticeArrayList:List<CommentResponse>)

            fun sendCommentDataSuccess(commentResponse: CommentResponse)
        }
    }

}