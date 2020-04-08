package com.example.bangkokchallenge.mypage

import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface MyPageContract {
    interface View:BaseView<Presenter>{
        //var presenter
    }

    interface Presenter:BasePresenter{
        //fun start()
    }
}