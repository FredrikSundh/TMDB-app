package com.example.tmdbapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
// Enda lösningen jag ser nu är att klona denna klass och skapa en ny entity tablename för att spara cacheade filmer där.
// kan behöva göra en databaseMovie Klass för att inserta hela listor för popular och top Rated Movies
// Samt en metod för att hämta dessa modeller och återskapa listor av filmer som sätts som livedata
// kan behöva göra en klass så att jag kan inserta listor av get movie Details responser och koppla så att movie details hämtar från databaen
// kan behöva göra en
@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey()
    @Json(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    @Json(name = "title")
    var movieTitle: String,

    @ColumnInfo(name = "poster_path")
    @Json(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    @Json(name ="backdrop_path")
    var backDropPath: String,

    @ColumnInfo(name = "release_date")
    @Json(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "overview")
    @Json(name = "overview")
    var description: String
) : Parcelable { // inherits parcelablle so it can be sent to other fragments
}