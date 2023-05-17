package com.example.tmdbapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.network.ConnectivityObserver

class MovieListViewModelFactory(private val connectivityObserver : ConnectivityObserver,private val movieDatabaseDao: MovieDatabaseDao, private val application : Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(connectivityObserver,movieDatabaseDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}