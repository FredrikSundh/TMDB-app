package com.example.tmdbapp

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.example.tmdbapp.database.Movies
import com.example.tmdbapp.databinding.FragmentFirstBinding
import com.example.tmdbapp.utils.constants

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       // _binding = FragmentFirstBinding.inflate(inflater, container, false)
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_first,container, false)
        val movies = Movies();

        binding.movies = movies

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