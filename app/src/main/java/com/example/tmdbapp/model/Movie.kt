package com.example.tmdbapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
 data class Movie(
     var movieTitle : String,
     var posterPath : String,
     var backDropPath : String,
     var releaseDate : String,
     var description : String
     ) : Parcelable { // inherits parcelablle so it can be sent to other fragments
}