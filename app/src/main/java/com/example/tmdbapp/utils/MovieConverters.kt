package com.example.tmdbapp.utils

import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.model.favouriteMovie

 fun convertMovieToFavourite(movie: Movie) : favouriteMovie {
    return favouriteMovie(
        id = movie.id,
        movieTitle = movie.movieTitle,
        posterPath = movie.posterPath,
        backDropPath = movie.backDropPath,
        releaseDate = movie.releaseDate,
        description = movie.description
    )

}

 fun convertFavouriteToMovie(favouriteMovie: favouriteMovie) : Movie {
    return Movie(
        id = favouriteMovie.id,
        movieTitle = favouriteMovie.movieTitle,
        posterPath = favouriteMovie.posterPath,
        backDropPath = favouriteMovie.backDropPath,
        releaseDate = favouriteMovie.releaseDate,
        description = favouriteMovie.description
    )

}