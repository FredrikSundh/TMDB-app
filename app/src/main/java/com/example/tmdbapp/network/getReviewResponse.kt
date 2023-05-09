package com.example.tmdbapp.network

import com.example.tmdbapp.model.MovieReview
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
class getReviewResponse {
    @Json(name = "results")
    var results : List<MovieReview> = listOf()
}