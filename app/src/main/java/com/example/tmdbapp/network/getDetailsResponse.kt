package com.example.tmdbapp.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class getDetailsResponse {
    @Json(name = "genres")
    var genres = listOf<Genre>()

    @Json(name = "imdb_id")
    var imdbID : String = ""

    @Json(name = "homepage")
    var homepage_url : String = ""

    @Json(name = "poster_path")
    var poster_path : String = ""


    class Genre() {
        @Json(name = "name")
        var genre : String = ""
    }

}