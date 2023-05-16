package com.example.tmdbapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.bumptech.glide.Glide.init
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.model.CachedType
import com.example.tmdbapp.network.TMDBApi
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.network.MovieResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MovieRepository(movieDatabaseDao: MovieDatabaseDao) {
    val dao = movieDatabaseDao
    val movies = MutableLiveData<List<Movie>>()
    var lastCalled = "popular"


    init {
        GlobalScope.launch {
            val moviesList = withContext(Dispatchers.IO) {
                dao.getAllMovies()
            }
            movies.postValue(moviesList)
        }
    }


    suspend fun refreshPopular(callType : String) { // Favourite filmer har mindre overhead tror jag så fixar en ny klass till den senare.
            withContext(Dispatchers.IO) {
                var movieResponse : MovieResponse
                if (callType == "popular") {
                    movieResponse = TMDBApi.movieListRetrofitService.getPopularMovies() // Retrieves movies and inserts entire movielist into database
                    val cached : CachedType = CachedType("popular")
                    dao.deleteAllCachedTypes()
                    dao.insertCachedType(cached) // Stores the cached Type
                }
                else { // if
                    movieResponse = TMDBApi.movieListRetrofitService.getTopRatedMovies() // Retrieves movies and inserts entire movielist into database
                    val cached : CachedType = CachedType("top rated")
                    dao.deleteAllCachedTypes()
                    dao.insertCachedType(cached) // Stores the cached Type
                }
                if(lastCalled != callType) { // if the requested call is not the current call the database needs to be cleared
                dao.deleteAll()
                }
                lastCalled = callType
                //movies.value = movieResponse.results
                movies.postValue(movieResponse.results)
                for (movie in movieResponse.results) {
                    dao.insert(movie)
                }
            }
    }
}