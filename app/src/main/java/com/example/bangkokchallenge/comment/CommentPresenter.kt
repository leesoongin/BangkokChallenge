package com.example.bangkokchallenge.comment

import com.example.bangkokchallenge.model.CommentItem
import org.w3c.dom.Comment

class CommentPresenter(
    private val commentView:CommentContract.View,
    private val commentInteractor:CommentContract.CommentInteractor
) :CommentContract.Presenter, CommentContract.CommentInteractor.OnFinishedListener{

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestCommentDateFromServer() {
        commentInteractor.getCommentDate(this)
    }

    override fun requestCloseCommentPage() {
        commentView.closeCommentPage()
    }

    override fun onSuccess(noticeArrayList:List<CommentItem>) {
        commentView.setRecyclerViewData(noticeArrayList)
    }

}