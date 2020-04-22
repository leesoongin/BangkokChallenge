package com.example.bangkokchallenge.comment

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.CommentItem

interface CommentContract {
    interface View:BaseView<Presenter>{
        fun setRecyclerViewData(responseData :List<CommentItem>)
    }

    interface Presenter:BasePresenter{
        fun requestCommentDateFromServer()
    }

    interface CommentInteractor{
        fun getCommentDate(onFinishedListener:OnFinishedListener)

        interface OnFinishedListener{
            fun onSuccess(noticeArrayList:List<CommentItem>)
        }
    }

}