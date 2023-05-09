package com.example.tmdbapp.model

import com.squareup.moshi.Json

data class MovieReview(

    @Json(name = "author")
    val username : String,

    @Json(name = "content")
    val content : String
)