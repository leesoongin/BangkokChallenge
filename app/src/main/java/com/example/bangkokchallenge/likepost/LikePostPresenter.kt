package com.example.bangkokchallenge.likepost

import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse

class LikePostPresenter(
        private val likePostView:LikePostContract.View,
        private val likePostInteractor:LikePostContract.LikePostInteractor
    )  : LikePostContract.Presenter,LikePostContract.LikePostInteractor.OnFinishedListener{

    override fun onLikePostSuccess(noticeArrayList: List<TimeLineItem>?) {
        likePostView.setRecyclerViewData(noticeArrayList)
    }

    override fun onLikeSuccess(likeResponse: LikeResponse) {
        likePostView.modifyLikeData(likeResponse)
    }

    override fun requestLikePostDataFromServer(token: String?) {
        likePostInteractor.getLikePostData(token,this)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickLike(postId: Int?, token: String?) {
        likePostInteractor.putLikeBySelf(postId,token, this)
    }

    override fun onClickDescription() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickComment(discription: String,hashTag : String, postId: Int?) {
        likePostView.openToCommentPage(discription,hashTag,postId)
    }

    override fun onFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}