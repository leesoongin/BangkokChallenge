package com.example.bangkokchallenge.timeline

import android.util.Log
import com.example.bangkokchallenge.model.TimeLineItem

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class TimeLinePresenter(
    private val timeLineView: TimeLineContract.View,
    private val timeLineInteractor : TimeLineContract.TimeLineInteractor
) : TimeLineContract.Presenter, TimeLineContract.TimeLineInteractor.OnFinishedListener {

    override fun start() {

    }

    override fun requestTimeLineDataFromServer() {
        timeLineInteractor.getTimeLineData(this)
    }

    override fun onClickLike(position: Int) {
        Log.d("@@@@ONCLICK","@@@@YEAH")
        timeLineInteractor.putLikeBySelf(position, this)
    }

    override fun onClickDescription() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickComment(discription:String) {
        timeLineView.openToCommentPage(discription)
    }

    override fun onTimeLineSuccess(noticeArrayList: List<TimeLineItem>) {
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