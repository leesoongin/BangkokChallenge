package com.example.bangkokchallenge.comment

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.CommentItem

interface CommentContract {
    interface View:BaseView<Presenter>{
        fun setRecyclerViewData(responseData :List<CommentItem>)
        fun closeCommentPage()
    }

    interface Presenter:BasePresenter{
        fun requestCommentDateFromServer()
        fun requestCloseCommentPage()
    }

    interface CommentInteractor{
        fun getCommentDate(onFinishedListener:OnFinishedListener)

        interface OnFinishedListener{
            fun onSuccess(noticeArrayList:List<CommentItem>)
        }
    }

}