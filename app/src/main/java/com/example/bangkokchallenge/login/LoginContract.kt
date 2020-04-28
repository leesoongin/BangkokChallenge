package com.example.bangkokchallenge.login

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.AccountDTO

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface LoginContract {
    interface View : BaseView<Presenter>{

        fun login()

        fun logout()

        fun loginSuccess(accountDTO: AccountDTO)

    }

    interface Presenter : BasePresenter{

        fun requestLogin()

        fun requestLogout()

        fun requestAccessToken(token : String)

    }

    interface LoginInteractor{

        fun sendAccessToken(token : String,onFinishedListener: OnFinishedListener) // 서버로 user session 전송

        interface OnFinishedListener{

            fun onSendTokenSuccess(accountDTO: AccountDTO)

            fun onSendTokenFailure(t: Throwable?)
        }
    }
}