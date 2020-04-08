package com.example.bangkokchallenge.timeline

/**
 * Created by choejun-yeong on 04/04/2020.
 */


class TimeLinePresenter (private val timeline_view:TimeLineContract.View) :TimeLineContract.Presenter{
    override fun requestLike() {
        timeline_view.pushLike() /*좋아요 호출*/
    }

    override fun requestComment() {
        timeline_view.pushComment() /*댓글창 호출*/
    }

    override fun requestMoreview() {
        timeline_view.pushMoreview() /*더보기 호출*/
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}