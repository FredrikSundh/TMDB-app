package com.example.tmdbapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.network.TMDBApi
import com.example.tmdbapp.model.Movie

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class MovieRepository(movieDatabaseDao: MovieDatabaseDao) {
    val dao = movieDatabaseDao
    val movies = MutableLiveData<List<Movie>>()

    suspend fun refreshPopular() { // Favourite filmer har mindre overhead tror jag så fixar en ny klass till den senare.
            withContext(Dispatchers.IO) {
                val movieResponse = TMDBApi.movieListRetrofitService.getPopularMovies() // Retrieves movies and inserts entire movielist into database
                movies.value = movieResponse.results

                for (movie in movieResponse.results) {
                    dao.insert(movie)
                }
            }
    }
}