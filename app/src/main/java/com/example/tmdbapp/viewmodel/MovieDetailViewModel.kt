package com.example.tmdbapp.viewmodel

import android.app.Application
import android.view.View
import androidx.annotation.Keep
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.network.DataFetchStatus
import com.example.tmdbapp.network.MovieResponse
import com.example.tmdbapp.network.TMDBApi
import com.example.tmdbapp.network.getDetailsResponse

import kotlinx.coroutines.launch
@Keep
class MovieDetailViewModel(
    private val movieId : String,
    private val movieDatabaseDao: MovieDatabaseDao,
    application: Application,
    movie: Movie
) : AndroidViewModel(application){
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() {
            return _isFavorite
        }

    init {
        setIsFavorite(movie)
    }

    private fun setIsFavorite(movie: Movie) {
        viewModelScope.launch {
            _isFavorite.value = movieDatabaseDao.isFavorite(movie.id.toLong())
        }
    }




    fun onSaveMovieButtonClicked(movie: Movie) {
        viewModelScope.launch {
            movieDatabaseDao.insert(movie)
            setIsFavorite(movie)
        }
    }

    fun onRemoveMovieButtonClicked(movie: Movie) {
        viewModelScope.launch {
            movieDatabaseDao.delete(movie)
            setIsFavorite(movie)
        }
    }
}