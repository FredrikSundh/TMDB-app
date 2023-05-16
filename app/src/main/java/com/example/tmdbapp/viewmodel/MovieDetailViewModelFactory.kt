package com.example.tmdbapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.viewmodel.MovieDetailViewModel
import java.lang.IllegalArgumentException

class MovieDetailViewModelFactory(private val movieId : String,
                                  private val movieDatabaseDao: MovieDatabaseDao,
                                  private val application: Application,
                                  private val movie: Movie
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieId, movieDatabaseDao, application, movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}