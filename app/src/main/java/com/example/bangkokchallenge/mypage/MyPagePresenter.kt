package com.example.bangkokchallenge.mypage

import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class MyPagePresenter(
    private val mypageView:MyPageContract.View,
    private val mypageInteractor: MyPageContract.MyPageInteractor
) :MyPageContract.Presenter , MyPageContract.MyPageInteractor.OnFinishedListener{

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickLike(postId: Int?, token: String?) {
        mypageInteractor.putLikeBySelf(postId,token, this)
    }

    override fun onClickDescription() {
       //딱히 없고
    }

    override fun onClickComment(discription: String,hashTag : String, postId: Int?) {
        mypageView.openToCommentPage(discription,hashTag,postId)
    }

    override fun requestMyPageDataFromSever(token: String?) {
        mypageInteractor.getMyPageData(token,this) //this -> MyPagePresenter
    }

    override fun onMyPageSuccess(noticeArrayList: List<TimeLineItem>?) {
        mypageView.setRecyclerViewData(noticeArrayList)
    }

    override fun onLikeSuccess(likeResponse: LikeResponse) {
        mypageView.modifyLikeData(likeResponse)
    }

    override fun onFailure(t: Throwable?) {
        mypageView.onResponseFailure(t)
    }

}