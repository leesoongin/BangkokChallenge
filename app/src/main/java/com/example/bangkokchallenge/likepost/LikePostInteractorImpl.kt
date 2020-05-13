package com.example.bangkokchallenge.likepost

import com.example.bangkokchallenge.model.TimeLineItem

class LikePostInteractorImpl :LikePostContract.LikePostInteractor{
    override fun getLikePostData(onFinishedListener: LikePostContract.LikePostInteractor.OnFinishedListener) {

        onFinishedListener.onSuccess(arrayListOf(
          /*  TimeLineItem("1","kim","imageUri","1번","2020-04-21","2020","","",""),
            TimeLineItem("2","lee","imageUri","2번","2020-04-21","2020",2,false,2),
            TimeLineItem("3","park","imageUri","3번","2020-04-21","2020",3,false,3)
    */    ))
    }

}