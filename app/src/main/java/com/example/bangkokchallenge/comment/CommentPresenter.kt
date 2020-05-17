package com.example.bangkokchallenge.comment

import android.util.Log
import com.example.bangkokchallenge.model.response.CommentResponse

class CommentPresenter(
    private val commentView:CommentContract.View,
    private val commentInteractor:CommentContract.CommentInteractor
) :CommentContract.Presenter, CommentContract.CommentInteractor.OnFinishedListener{

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestCommentDataFromServer(token : String?,postId : Int) {
        commentInteractor.getCommentData(this,token,postId)
    }

    override fun requestSendCommentDataToServer(token : String?,comment:String?,postId :Int) {
        commentInteractor.sendCommentData(this,token,comment,postId)

    }

    override fun requestCloseCommentPage() {
        commentView.closeCommentPage()
    }

    override fun getCommentDataSuccess(noticeArrayList: List<CommentResponse>) {
        commentView.setRecyclerViewData(noticeArrayList)
    }

    override fun sendCommentDataSuccess(commentResponse: CommentResponse) {
       Log.d("@@sendComment2","${commentResponse}")
    }

}