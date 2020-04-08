package com.example.bangkokchallenge.timeline

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface TimeLineContract{
    interface View :BaseView<Presenter>{
        /* 이벤트 listener */
        fun pushLike()
        fun pushComment()
        fun pushMoreview()
    }
    interface Presenter:BasePresenter{
        /* 좋아요 listener 댓글 listener 상세보기 -> request */
        fun requestLike()
        fun requestComment()
        fun requestMoreview()
    }
}