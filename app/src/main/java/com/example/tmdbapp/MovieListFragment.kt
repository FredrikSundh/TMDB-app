package com.example.tmdbapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.tmdbapp.database.Movies
import com.example.tmdbapp.databinding.FragmentMovieListBinding
import com.example.tmdbapp.databinding.MovielistBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {


    private var _binding: FragmentMovieListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout for this fragment
        _binding = FragmentMovieListBinding.inflate(layoutInflater)
        val movies = Movies();
        movies.movieList.forEach { movie ->
            val movielistBinding : MovielistBinding = DataBindingUtil.inflate(inflater,R.layout.movielist, container, false)
            movielistBinding.movie = movie
            binding.movieListLinearLayout.addView(movielistBinding.root);

            movielistBinding.root.setOnClickListener {
                this.findNavController().navigate(MovieListFragmentDirections.actionMovieListToMovieDetails(movie))
            }



        }


        Log.i("returning","returning root")
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        //val movies = Movies();

        /**
         * MovieList är en lista som implementerar ett layout: movie Item.
         * Movie item laddar en film, movie item har i sin tur flera olika views
         * som attribut dvs movie_picture, movie_description och moviedate
         */
/**
        val movieList = view.findViewById<LinearLayout>(R.id.movie_list_linear_layout);
        val movieItem = movieList.findViewById<View>(R.id.movie_1);
        val movieTitle = movieItem.findViewById<TextView>(R.id.movietitle);
        val moviePoster = movieItem.findViewById<ImageView>(R.id.movie_picture); // movie poster is the movie picture of movie_1


        // Load nedan lägger ihop bas urlen plus bildstorlek och posterpathen från movie list
        // för att skapa en komplett url till tmdb
        //


        movieTitle.text = movies.movieList[0].movieTitle;
        Glide
            .with(this)
            .load(constants.POSTER_IMAGE_BASE_URL + constants.POSTER_IMAGE_WIDTH + movies.movieList[0].posterPath)
            .into(moviePoster)

*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}