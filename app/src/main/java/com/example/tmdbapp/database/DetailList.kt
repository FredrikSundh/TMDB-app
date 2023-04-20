package com.example.tmdbapp.database

import com.example.tmdbapp.model.MovieDetails

class DetailList {
    val detailList = mutableListOf<MovieDetails>()

    init {

        detailList.add( // Mario
            MovieDetails(
                "502356",
                "Animation, Adventure, Family, Fantasy, Comedy",
                "https://www.thesupermariobros.movie",
                "tt6718170"
            )
        )

        detailList.add( // Avatar the way of water
            MovieDetails(
                "76600",
                "Science Fiction, Adventure, Action",
                "https://www.avatar.com/m…/avatar-the-way-of-water",
                "tt1630029"
            )
        )

        detailList.add( // Shazam
            MovieDetails(
                "594767",
                "Action, Comedy, Fantasy",
                "No homepage",
                "tt10151854"
            )
        )

        detailList.add( // Murder mystery 2
            MovieDetails(
                "638974",
                "Comedy, Mystery, Action",
                "https://www.netflix.com/title/81212842",
                "tt15255288"
            )
        )



        detailList.add( // Creed 3
            MovieDetails(
                "677179",
                "Drama, Action",
                "https://www.mgmstudios.com/creed-iii",
                "tt11145118"
            )
        )

    }


}