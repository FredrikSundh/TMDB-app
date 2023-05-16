package com.example.tmdbapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.database.MovieDatabaseDao

class MovieListViewModelFactory(private val movieDatabaseDao: MovieDatabaseDao, private val application : Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(movieDatabaseDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}