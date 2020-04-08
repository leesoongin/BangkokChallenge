package com.example.bangkokchallenge.login

import com.example.bangkokchallenge.createpost.CreatePostContract

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class LoginPresenter(private val loginView: LoginContract.View) : LoginContract.Presenter {

    override fun start() {

    }

    override fun requestLogin() {
        loginView.login()
    }

    override fun requestLogout() {
        loginView.logout()
    }
}