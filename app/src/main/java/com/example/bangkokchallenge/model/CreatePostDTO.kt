package com.example.bangkokchallenge.model

import android.net.Uri

data class CreatePostDTO(
    val token : String?,
    val uriList : List<Uri>?,
    val article : String?,
    val hashTag : String?
)