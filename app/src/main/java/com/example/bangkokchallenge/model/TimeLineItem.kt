package com.example.bangkokchallenge.model

import java.util.*

/**
 * Created by choejun-yeong on 08/04/2020.
 */
data class TimeLineDTO(
    val postList : List<TimeLineItem>?
)

data class TimeLineItem( //임시 모델.
    var id : String?, //kakao id
    var accountId : String?,
    var nickname : String?, //kakao nicname
    var profile_photo : String, // 프로필사진 url
    var filePath : String?, //or List<String>
    var article : String?, //내용
    var createdAt : String?, // 생성날짜
    var modifiedAt : String?,
    var hashTag : List<HashTag>?,
    var likeCount : Int?, //좋아요개수
    var selfLike : Boolean, //좋아요 여부
    var commentCount: Int?// 댓글개수
)
