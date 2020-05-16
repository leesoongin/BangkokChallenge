package com.example.bangkokchallenge.createpost

import com.example.bangkokchallenge.model.CreatePostDTO

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class CreatePostPresenter(
    private val createPostView: CreatePostContract.View,
    private val createPostInteractor: CreatePostContract.CreatePostInteractor
) : CreatePostContract.Presenter,CreatePostContract.CreatePostInteractor.OnFinishedListener {

    override fun requestPosting(createPostDTO : CreatePostDTO) {
        createPostView.posting()
        createPostInteractor.sendPost(this,createPostDTO)

    }

    override fun OnSendPostSuccess() {

    }

    override fun start() {
        //Todo : 준영이형 보고싶다
    }
}