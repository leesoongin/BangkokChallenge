package com.example.bangkokchallenge.timeline

import android.util.Log
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.ResponseModel

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class TimeLinePresenter(
    private val timeLineView: TimeLineContract.View,
    private val timeLineInteractor : TimeLineContract.TimeLineInteractor
) : TimeLineContract.Presenter, TimeLineContract.TimeLineInteractor.OnFinishedListener {

    override fun start() {

    }

    override fun requestTimeLineDataFromServer(token : String?) {
        timeLineInteractor.getTimeLineData(token,this)
    }

    override fun onClickLike(position: Int) {
        timeLineInteractor.putLikeBySelf(position, this)
    }

    override fun onClickDescription() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickComment(discription:String,postId:Int?) {
        timeLineView.openToCommentPage(discription,postId)
    }

    override fun onTimeLineSuccess(noticeArrayList: List<TimeLineItem>?) {
        timeLineView.setRecyclerViewData(noticeArrayList)
    }

    override fun onTimeLineFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLikeSuccess(position: Int, isLike: Boolean) {
        timeLineView.modifyLikeData(position,isLike)
    }

    override fun onLikeFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}