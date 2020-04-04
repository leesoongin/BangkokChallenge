package com.example.bangkokchallenge.login

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bangkokchallenge.R

/**
 * Created by choejun-yeong on 04/04/2020.
 */


class LoginActivity  : AppCompatActivity(), LoginContract.View{

    private lateinit var loginButton: Button
    private lateinit var logoutButton: Button
    private lateinit var loginStateText : TextView

    override lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViewBinding()

        presenter = LoginPresenter(this)

    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    private fun initViewBinding(){
        loginButton = findViewById(R.id.login_button)
        logoutButton = findViewById(R.id.logout_button)
        loginStateText = findViewById(R.id.login_state_text)

        loginButton.setOnClickListener {
            presenter.requestLogin()
        }

        logoutButton.setOnClickListener {
            presenter.requestLogout()
        }
    }

    override fun changeLoginState(boolean: Boolean) {
        if(boolean){
            loginStateText.text = "로그인 상태"
        }else{
            loginStateText.text = "로그아웃 상태"
        }
    }
}