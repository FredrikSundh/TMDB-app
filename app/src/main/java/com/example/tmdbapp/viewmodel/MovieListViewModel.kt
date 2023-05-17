package com.example.tmdbapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.network.ConnectivityObserver
import com.example.tmdbapp.network.DataFetchStatus
import com.example.tmdbapp.network.MovieResponse
import com.example.tmdbapp.network.TMDBApi
import com.example.tmdbapp.utils.convertFavouriteToMovie
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MovieListViewModel(private val connectivityObserver: ConnectivityObserver,private val movieDatabaseDao: MovieDatabaseDao, application : Application) : AndroidViewModel(application) {

    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus : LiveData<DataFetchStatus>
    get() {
        return _dataFetchStatus
    }
/**
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<Movie>>
    get() {
        return _movieList
    }
**/
var displaying : String? = "popular"
    private val _navigateToMovieDetail = MutableLiveData<Movie?>()
    val navigateToMovieDetail: MutableLiveData<Movie?>
    get() {
        return _navigateToMovieDetail
    }
    val repository = MovieRepository(movieDatabaseDao)
    val movieList = repository.movies
    val connectionObserver : ConnectivityObserver = connectivityObserver
    var cachedType : String? = ""




    init {
        val job2 = GlobalScope.launch {
            cachedType = movieDatabaseDao.getFirstCachedType()
        }
        runBlocking {
            job2.join()
        }
        setupObserver()
        //val movieList = Movies()
        //_movieList.value =  movieList.movieList
        //getPopularMovies()
        getPopularFromRepo(cachedType)
        Log.i("called getfromrepo with","$cachedType")
    }

    fun setupObserver() {
        connectionObserver.observe().onEach {
            if(it == ConnectivityObserver.Status.Available && displaying != "saved") {
                Log.i("in if", "calling get repo")
                getPopularFromRepo(displaying)
            }
            Log.i("network status is", "$it")
        }.launchIn(GlobalScope)

    }



// There is no way to determine of what type the cached data is on startup
// For this reason I will create a new database Entity which i will store and delete
// this database entry will only tell me the current cachetype.
// by getting this object from the database you can determine which type of movies are cached
// and program the correct Behaviour, By doing this i wont need new movietypes and converters
fun getPopularFromRepo( callType: String?) {

    val job = GlobalScope.launch {
            cachedType = movieDatabaseDao.getFirstCachedType()
    }
    runBlocking {// Ensures that the type of cached movies is resolved before proceeding
        job.join()
    }
    viewModelScope.launch {
        if(callType == "popular") {
            Log.i("hello", "in calltype popular")
            displaying = callType
            // try to do refreshpopular call, if it fails load movies from repo list
            try {
                repository.refreshPopular("popular")
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e: Exception) {
                if(cachedType == "popular") {
                    Log.i("hello","in cachedtype popular")
                    _dataFetchStatus.value = DataFetchStatus.DONE
                    repository.movies.postValue(movieDatabaseDao.getAllMovies())
                    return@launch
                }
                // This triggers when there is no internet and the requested type isnt in the cache
                // Also needs to post empty value to th repo
                val asd = listOf<Movie>()
                repository.movies.postValue(asd)
                _dataFetchStatus.value = DataFetchStatus.ERROR
            }
        }
        else { // if calltype isnt popular, its top rated
            displaying = callType
            try {
                repository.refreshPopular("top rated")
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch (e: Exception) {
                if(cachedType == "top rated") {
                    _dataFetchStatus.value = DataFetchStatus.DONE
                    repository.movies.postValue(movieDatabaseDao.getAllMovies())
                    return@launch
                }
                val asd = listOf<Movie>()
                repository.movies.postValue(asd)
                _dataFetchStatus.value = DataFetchStatus.ERROR
            }

        }
        if (cachedType != null) {
            Log.i("checking String", cachedType!!)
        }
    }


}


    fun getSavedMovies() {
        displaying = "saved"
        viewModelScope.launch {
            val favouriteMovielist = movieDatabaseDao.getFavourites()
            var newMovieList = mutableListOf<Movie>()
            for(movie in favouriteMovielist) {
               var interim = convertFavouriteToMovie(movie) // converts the favourite to movie and stors in variable to add to list
                newMovieList.add(interim)
            }
            repository.movies.postValue(newMovieList)
            _dataFetchStatus.value = DataFetchStatus.DONE
        }

    }
    fun onMovieListItemClicked(movie: Movie) {
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }




}