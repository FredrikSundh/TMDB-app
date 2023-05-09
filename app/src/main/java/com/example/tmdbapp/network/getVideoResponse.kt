package com.example.tmdbapp.network

import com.example.tmdbapp.model.VideoKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class getVideoResponse {
    @Json(name = "results")
    var results = listOf<VideoKey>() // Gets a list of url keys that can be concatenated with base youtube url to load a video
}