package com.example.bangkokchallenge.likepost

import com.example.bangkokchallenge.model.TimeLineItem

class LikePostPresenter(
        private val likePostView:LikePostContract.View,
        private val likePostInteractor:LikePostContract.LikePostInteractor
    )  : LikePostContract.Presenter,LikePostContract.LikePostInteractor.OnFinishedListener{
    override fun requestDataFromServer() {
        likePostInteractor.getLikePostData(this)
    }

    override fun onSuccess(noticeArrayList: List<TimeLineItem>) {
        likePostView.setRecyclerViewData(noticeArrayList)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}