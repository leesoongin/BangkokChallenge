package com.example.bangkokchallenge.timeline

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

    override fun onSuccess(noticeArrayList: List<TimeLineItem>) {
        timeLineView.setRecyclerViewData(noticeArrayList)
    }

    override fun onFailure(t: Throwable?) {
        timeLineView.onResponseFailure(t)
    }
}