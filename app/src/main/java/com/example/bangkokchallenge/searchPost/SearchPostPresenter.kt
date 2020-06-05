package com.example.bangkokchallenge.searchPost

import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse

class SearchPostPresenter(
    private val searchPostView: SearchPostContract.View,
    private val searchPostInteractor : SearchPostContract.SearchPostInteractor
) : SearchPostContract.Presenter, SearchPostContract.SearchPostInteractor.OnFinishedListener {

    override fun start() {

    }

    override fun requestSearchPostDataFromServer(token : String?,hashTag : String?) {
        searchPostInteractor.getSearchPostData(token,hashTag,this)
    }

    override fun onClickLike(postId : Int?,token : String?) {
        searchPostInteractor.putLikeBySelf(postId,token, this)
    }

    override fun onClickComment(discription:String,hashTag : String,postId:Int?) {
        searchPostView.openToCommentPage(discription,hashTag,postId)
    }

    override fun onClickDescription() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchPostSuccess(noticeArrayList: List<TimeLineItem>?) {
        searchPostView.setRecyclerViewData(noticeArrayList)
    }

    override fun onSearchPostFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLikeSuccess(likeResponse: LikeResponse) {
        searchPostView.modifyLikeData(likeResponse)
    }

    override fun onLikeFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}