package com.example.bangkokchallenge.model.response

data class LikeResponse(
    val postId : Int, // postId
    val likeTrueAndFalse : Boolean,
    val likeCount : Int
)
/*

{
  "createdAt" : "2020-05-14 23:50:31",
  "modifiedAt" : "2020-05-14 23:50:31",
  "id" : 41,
  "postId" : 1,
  "accountId" : "TDD_TEMP_ID",
  "likeTrueAndFalse" : true,
  "likeCount" : 1,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/like/1"
    },
    "Post list" : {
      "href" : "http://localhost:8080/api/post"
    },
    "profile" : {
      "href" : "/docs/index.html#resource-changeLikeState"
    }
  }
}
* */