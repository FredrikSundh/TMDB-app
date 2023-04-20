package com.example.tmdbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.database.Movies

class MovieListViewModel(application : Application) : AndroidViewModel(application) {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<Movie>>
    get() {
        return _movieList
    }

    init {
        _movieList.postValue(Movies().movieList)
    }




}