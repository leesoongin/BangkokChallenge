package com.example.bangkokchallenge.mypage

import com.example.bangkokchallenge.model.TimeLineItem

class MyPageInteractorImpl : MyPageContract.MyPageInteractor{
    override fun getMyPageData(onFinishedListener: MyPageContract.MyPageInteractor.OnFinishedListener) {
        /* 서버에서 받아와 dataList에 담기*/

        onFinishedListener.onSuccess(arrayListOf(
           /* TimeLineItem("1","lee","imageUri","안녕 1번이야","2020-04-21","2020",1,false,1),
            TimeLineItem("2","park","imageUri","안녕 2번이야","2020-04-21","2020",1,false,2),
            TimeLineItem("3","kim","imageUri","안녕 3번이야","2020-04-21","2020",1,false,3)*/
        ))
    }


}