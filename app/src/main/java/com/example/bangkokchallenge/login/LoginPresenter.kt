package com.example.bangkokchallenge.login

import com.example.bangkokchallenge.createpost.CreatePostContract
import com.example.bangkokchallenge.model.AccountDTO
import com.kakao.network.response.ResponseBody

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class LoginPresenter(private val loginView: LoginContract.View,
                        private val loginInteractor : LoginContract.LoginInteractor)
    : LoginContract.Presenter ,LoginContract.LoginInteractor.OnFinishedListener{

    override fun start() {

    }

    override fun requestLogin() {
        loginView.login()
    }

    override fun requestLogout() {
        loginView.logout()
    }

    override fun requestAccessToken(token: String) {
        loginInteractor.sendAccessToken(token, this)
    }

    override fun onSendTokenSuccess(accountDTO: AccountDTO) {
        loginView.loginSuccess(accountDTO)
    }

    override fun onSendTokenFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}