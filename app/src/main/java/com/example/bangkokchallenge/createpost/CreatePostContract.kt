package com.example.bangkokchallenge.createpost

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface CreatePostContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {
        fun requestPosting()
    }

    interface CreatePostInteractor{
        fun sendPost(onFinishedListener : OnFinishedListener)

        interface OnFinishedListener{
            fun OnSendPostSuccess()
        }
    }
}