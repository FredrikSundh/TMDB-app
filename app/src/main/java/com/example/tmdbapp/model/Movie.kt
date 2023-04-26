package com.example.tmdbapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    @Json(name = "id")
    val id: String,

    @Json(name = "title")
    var movieTitle: String,

    @Json(name = "poster_path")
    var posterPath: String,

    @Json(name ="backdrop_path")
    var backDropPath: String,

    @Json(name = "release_date")
    var releaseDate: String,

    @Json(name = "overview")
    var description: String
) : Parcelable { // inherits parcelablle so it can be sent to other fragments
}