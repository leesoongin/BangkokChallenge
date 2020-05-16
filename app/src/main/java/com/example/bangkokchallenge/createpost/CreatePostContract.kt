package com.example.bangkokchallenge.createpost

import android.net.Uri
import com.example.bangkokchallenge.base.BasePresenter
import com.example.bangkokchallenge.base.BaseView
import com.example.bangkokchallenge.model.CreatePostDTO

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface CreatePostContract {

    interface View : BaseView<Presenter> {
        fun posting()
    }

    interface Presenter : BasePresenter {
        fun requestPosting(createPostDTO : CreatePostDTO)
    }

    interface CreatePostInteractor{
        fun sendPost(onFinishedListener : OnFinishedListener,createPostDTO: CreatePostDTO)

        interface OnFinishedListener{
            fun OnSendPostSuccess()
        }
    }
}