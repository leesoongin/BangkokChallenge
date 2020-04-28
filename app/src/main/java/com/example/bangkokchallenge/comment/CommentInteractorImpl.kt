package com.example.bangkokchallenge.comment

import com.example.bangkokchallenge.model.CommentItem

class CommentInteractorImpl :CommentContract.CommentInteractor{
    override fun getCommentDate(onFinishedListener: CommentContract.CommentInteractor.OnFinishedListener) {

        onFinishedListener.onSuccess(arrayListOf(
            CommentItem("image","kim","hi_1"),
            CommentItem("image","lee","hi_2"),
            CommentItem("image","park","hi_3"),
            CommentItem("image","seo","hi_4"),
            CommentItem("image","seo","hi_4"),
            CommentItem("image","seo","hi_4"),
            CommentItem("image","seo","hi_4"),
            CommentItem("image","seo","hi_4"),
            CommentItem("image","seo","hi_4"),
            CommentItem("image","seo","hi_4"),
            CommentItem("image","seo","hi_4")
        ))
    }

}