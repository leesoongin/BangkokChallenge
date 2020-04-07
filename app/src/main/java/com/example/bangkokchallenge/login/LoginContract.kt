package com.example.bangkokchallenge.login

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface LoginContract {
    interface View : BaseView<Presenter>{
        fun login()
        fun logout()
    }

    interface Presenter : BasePresenter{
        fun requestLogin()
        fun requestLogout()
    }
}