package com.example.bangkokchallenge.model.response

data class CommentResponse(
    val id : Int,
    val content : String ,
    val createAt : String,
    val modifiedAt : String,
    val postId : Int,
    val accountId : String,
    val profile_photo : String,
    val nickname : String
)

/*
* [ {
  "id" : 1,
  "content" : "test",
  "createdAt" : "2020-05-13 01:01:26",
  "modifiedAt" : "2020-05-13 01:01:26",
  "postId" : 11,
  "accountId" : "TEST_ID",
  "profile_photo" : "TEST_PROFILEPT",
  "nickname" : "TEST_NICKNAME"
} ]
* */