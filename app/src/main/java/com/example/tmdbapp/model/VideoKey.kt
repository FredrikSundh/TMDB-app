package com.example.tmdbapp.model

import com.squareup.moshi.Json

data class VideoKey (
    @Json(name = "key") // loads the api field key into the string value key
    val key : String
)