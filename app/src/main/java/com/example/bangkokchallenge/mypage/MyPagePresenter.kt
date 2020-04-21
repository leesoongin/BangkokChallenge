package com.example.bangkokchallenge.mypage

import com.example.bangkokchallenge.model.TimeLineItem

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

    override fun requestMyPageDataFromSever() {
        mypageInteractor.getMyPageData(this) //this -> MyPagePresenter
    }

    override fun onSuccess(noticeArrayList: List<TimeLineItem>) {
       mypageView.setRecyclerViewData(noticeArrayList)
    }

    override fun onFailure(t: Throwable?) {
        mypageView.onResponseFailure(t)
    }

}