package com.example.tmdbapp

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tmdbapp.database.DetailList
import com.example.tmdbapp.databinding.FragmentMovieDetailBinding
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.model.MovieDetails
import com.example.tmdbapp.utils.URLhandler

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private lateinit var movie: Movie

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView( // Skapa detailList och kolla ID för att hitta rätt film
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieDetailBinding.inflate(inflater)
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).Movie
        binding.movie = movie
        val moviedetails : DetailList = DetailList()
       val detail = when(movie.id) {
            "502356" -> moviedetails.detailList[0] // Mario
            "76600" -> moviedetails.detailList[1] // Avatar
            "594767" -> moviedetails.detailList[2] // Shazam
            "638974" -> moviedetails.detailList[3] // Murder mystery
            else -> moviedetails.detailList[4] // Creed
       }
        binding.moviedetails = detail
        binding.movieGenre.text = "Genre(s): " + detail.movieGenre

        val handler : URLhandler = URLhandler() // used to call openUrl to handle links

        binding.movieHomepage.text = "Movie Homepage: " + detail.homePageURL

        binding.movieHomepage.setOnClickListener{
            handler.openURL(binding.root.context,detail.homePageURL)
        }

         //Adding Link to homepage
        //binding.movieHomepage.setText(Html.fromHtml(detail.homePageURL))
        //Linkify.addLinks(binding.movieHomepage,Linkify.ALL)
        //binding.movieHomepage.setMovementMethod(LinkMovementMethod.getInstance())

        //Adding Link to IMDB
        binding.imdbUrl.setText(Html.fromHtml("https://www.imdb.com/title/" + detail.imdbID))
        Linkify.addLinks(binding.imdbUrl,Linkify.ALL)
        binding.movieHomepage.setMovementMethod(LinkMovementMethod.getInstance())



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToMovielist.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailsToMovieList())
        }
        binding.thirdFragmentButton.setOnClickListener {
            findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailsToThirdFragment(movie))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}