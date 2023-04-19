package com.example.tmdbapp.database

import com.example.tmdbapp.model.Movie

class Movies {
    val movieList = mutableListOf<Movie>()

    init {


        movieList.add(
            Movie(
                "The Super Mario Bros. Movie",
                "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                "/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg",
                "2023-04-05",
                "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi."
            )
        )

        movieList.add(
            Movie(
                "Avatar: The Way of Water",
                "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                "/ovM06PdF3M8wvKb06i4sjW3xoww.jpg",
                "2022-12-14",
                "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure."            )
        )

        movieList.add(
            Movie(
                "Shazam! Fury of the Gods",
                "/A3ZbZsmsvNGdprRi2lKgGEeVLEH.jpg",
                "/wybmSmviUXxlBmX44gtpow5Y9TB.jpg",
                "2023-03-15",
                "Billy Batson and his foster siblings, who transform into superheroes by saying Shazam!, are forced to get back into action and fight the Daughters of Atlas, who they must stop from using a weapon that could destroy the world."            )
        )

        movieList.add(
            Movie(
                "Murder Mystery 2",
                "/bT3IpP7OopgiVuy6HCPOWLuaFAd.jpg",
                "/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg",
                "2023-03-28",
                "After starting their own detective agency, Nick and Audrey Spitz land a career-making case when their billionaire pal is kidnapped from his wedding."            )
        )

        movieList.add(
            Movie(
                "Creed III",
                "/cvsXj3I9Q2iyyIo95AecSd1tad7.jpg",
                "/5i6SjyDbDWqyun8klUuCxrlFbyw.jpg",
                "2023-03-01",
                "After dominating the boxing world, Adonis Creed has been thriving in both his career and family life. When a childhood friend and former boxing prodigy, Damien Anderson, resurfaces after serving a long sentence in prison, he is eager to prove that he deserves his shot in the ring. The face-off between former friends is more than just a fight. To settle the score, Adonis must put his future on the line to battle Damien — a fighter who has nothing to lose."
            )
        )


    }

}