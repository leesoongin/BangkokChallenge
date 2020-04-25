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
    var dateTime : String?,
    var likeCount : Int?,
    var selfLike : Boolean,
    var commentCount: Int?
)
/*
 String id;
    @Id
    Integer post_Index;
    String article;
    String hasgTag[];
    String file;
    Integer likeNum;
    boolean selfLike;
    Integer commentNum;
    String filePath;
    Integer fileNum;
 */