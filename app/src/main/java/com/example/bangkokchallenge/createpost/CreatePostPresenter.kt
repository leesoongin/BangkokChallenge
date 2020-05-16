package com.example.bangkokchallenge.createpost

import com.example.bangkokchallenge.model.CreatePostDTO

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class CreatePostPresenter(
    private val createPostView: CreatePostContract.View,
    private val createPostInteractor: CreatePostContract.CreatePostInteractor
) : CreatePostContract.Presenter,CreatePostContract.CreatePostInteractor.OnFinishedListener {

    override fun pressedUploadButton() { // 버튼 눌림
        createPostView.posting()
    }

    override fun requestUploadPost(createPostDTO: CreatePostDTO) { // 눌린후 서버에 업로드 요청
        createPostInteractor.sendPost(this,createPostDTO)
    }

    override fun OnSendPostSuccess() {
        createPostView.finishUpload()
    }

    override fun start() {
        //Todo : 준영이형 보고싶다
    }
}