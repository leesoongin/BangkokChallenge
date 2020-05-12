package com.example.bangkokchallenge.model.response

import java.util.*

data class UploadResponse(
    val createAt : Date
)

/*
"createdAt" : "2020-04-30T22:12:02.444274",
  "modifiedAt" : "2020-04-30T22:12:02.444274",
  "userId" : "TDD_TEMP_ID",
  "id" : 12,
  "article" : "테스트내용",
  "hashTag" : "일상",
  "nickname" : "TDD_NICKNAME",
  "profile_photo" : "TDD_NICKNAME",
  "selfLike" : false,
  "commentCount" : 0,
  "likeCount" : 0,
  "filePath" : "https://jayass3cloud.s3.ap-northeast-2.amazonaws.com/hello.txt",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/post"
    },
    "profile" : {
      "href" : "/docs/index.html#resource-post-upload"
    }
  }
 */