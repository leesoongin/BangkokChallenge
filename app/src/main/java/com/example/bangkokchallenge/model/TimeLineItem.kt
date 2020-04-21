package com.example.bangkokchallenge.model

import java.util.*

/**
 * Created by choejun-yeong on 08/04/2020.
 */

data class TimeLineItem( //임시 모델.
    var id : String?,
    var userName : String?,
    var imageUrl : String?, //or List<String>
    var discription : String?,
    var dataTime : String?,
    var likeCount : Int?,
    var commentCount: Int?
)