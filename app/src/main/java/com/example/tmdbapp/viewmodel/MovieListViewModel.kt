package com.example.tmdbapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.network.DataFetchStatus
import com.example.tmdbapp.network.MovieResponse
import com.example.tmdbapp.network.TMDBApi
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieDatabaseDao: MovieDatabaseDao, application : Application) : AndroidViewModel(application) {

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus : LiveData<DataFetchStatus>
    get() {
        return _dataFetchStatus
    }

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<Movie>>
    get() {
        return _movieList
    }

    private val _navigateToMovieDetail = MutableLiveData<Movie?>()
    val navigateToMovieDetail: MutableLiveData<Movie?>
    get() {
        return _navigateToMovieDetail
    }
    val repository = MovieRepository(movieDatabaseDao)

    init {
        //val movieList = Movies()
        //_movieList.value =  movieList.movieList
        getPopularMovies()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                val movieResponse: MovieResponse = TMDBApi.movieListRetrofitService.getPopularMovies()
                _movieList.value = movieResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
            }catch(e: Exception) {
                _movieList.value = arrayListOf()
                _dataFetchStatus.value = DataFetchStatus.ERROR
            }
        }
    }



    fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                val movieResponse: MovieResponse =
                    TMDBApi.movieListRetrofitService.getTopRatedMovies()
                _movieList.value = movieResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _movieList.value = arrayListOf()
            }
        }
    }


    fun getSavedMovies() {
        viewModelScope.launch {
            _movieList.value = movieDatabaseDao.getAllMovies()
        }

    }
    fun onMovieListItemClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }




}