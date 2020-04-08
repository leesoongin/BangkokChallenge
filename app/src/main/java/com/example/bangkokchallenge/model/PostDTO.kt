package com.example.bangkokchallenge.model

data class PostDTO(var profilePath:String,
                   var nickname:String,
                  // var contentPath:ArrayList<String>,
                   var likeCount:Int,
                   var commentCount:Int,
                   var description:String,
                   var postDate:String) {

}