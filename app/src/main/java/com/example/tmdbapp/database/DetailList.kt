package com.example.tmdbapp.database

import com.example.tmdbapp.model.MovieDetails

class DetailList {
    val detailList = mutableListOf<MovieDetails>()

    init {

        detailList.add( // Mario
            MovieDetails(
                "Adventure",
                "https://www.thesupermariobros.movie",
                "tt6718170"
            )
        )

        detailList.add( // Avatar the way of water
            MovieDetails(
                "Science Fiction",
                "https://www.avatar.com/m…/avatar-the-way-of-water",
                "tt1630029"
            )
        )

        detailList.add( // Shazam
            MovieDetails(
                "Action",
                "No homepage",
                "tt10151854"
            )
        )

        detailList.add( // Murder mystery 2
            MovieDetails(
                "Comedy",
                "https://www.netflix.com/title/81212842",
                "tt15255288"
            )
        )



        detailList.add( // Creed 3
            MovieDetails(
                "Drama",
                "https://www.mgmstudios.com/creed-iii",
                "tt11145118"
            )
        )

    }


}