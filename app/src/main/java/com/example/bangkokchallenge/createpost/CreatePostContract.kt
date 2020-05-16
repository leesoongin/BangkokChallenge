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
        fun setPostDataAndRequestUpload()

        fun finishUpload()
    }


    interface Presenter : BasePresenter {
        fun pressedUploadButton() //버튼 눌렸을때의 article, hashTag , selectedUriList 를 담은 createPostDTO 생성, requestUploadPost 호출

        fun requestUploadPost(createPostDTO : CreatePostDTO)
    }


    interface CreatePostInteractor {
        fun sendPost(onFinishedListener: OnFinishedListener, createPostDTO: CreatePostDTO)

        interface OnFinishedListener {
            fun OnSendPostSuccess() //일단 response 안쓸거니까 뭐 받지는 말고 액티비티 finish만 하자
        }
    }
}
