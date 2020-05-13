package com.example.bangkokchallenge.createpost

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class CreatePostPresenter(
    private val createPostView: CreatePostContract.View,
    private val createPostInteractor: CreatePostContract.CreatePostInteractor
) : CreatePostContract.Presenter,CreatePostContract.CreatePostInteractor.OnFinishedListener {

    override fun requestPosting() {
        createPostInteractor.sendPost(this)
    }

    override fun OnSendPostSuccess() {

    }

    override fun start() {
        //Todo : 준영이형 보고싶다
    }
}