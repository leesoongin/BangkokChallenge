package com.example.bangkokchallenge.model.response

data class ResponseModel<T>(
    val _embedded : T,
    val page : PageInfo
)

data class PageInfo(
    val size : Int,
    val totalElements : Int,
    val totalPages : Int,
    val number : Int
)