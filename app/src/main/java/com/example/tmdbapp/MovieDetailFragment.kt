package com.example.tmdbapp

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tmdbapp.database.DetailList
import com.example.tmdbapp.database.MovieDatabase
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.databinding.FragmentMovieDetailBinding
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.model.MovieDetails
import com.example.tmdbapp.network.TMDBApi
import com.example.tmdbapp.network.getDetailsResponse
import com.example.tmdbapp.utils.URLhandler
import com.example.tmdbapp.viewmodel.MovieDetailViewModel
import com.example.tmdbapp.viewmodel.MovieDetailViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var viewModelFactory: MovieDetailViewModelFactory
    private lateinit var  movieDatabaseDao: MovieDatabaseDao
    private lateinit var movieDetailsResponse : getDetailsResponse


    private var _binding: FragmentMovieDetailBinding? = null
    private lateinit var movie: Movie

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView( // Skapa detailList och kolla ID för att hitta rätt film
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO, when created the movie id should be checked and then do an apicall through
        //the viewmodel to obtain the movie details and set the detail data binding to
        //generate the genres and links

        //När man kommer tillbaka från detailfragment ska man tillbaka dit man va ex saved movies
        // inte alltid popular
        // hint override onresumefunction Kalla onresume {super onresume när man trycker på previous}
        // to determine which list was selected when you call the onresume function
        //använd preferences DataStore från kotlin unit 5 pathway 2
        _binding = FragmentMovieDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).Movie
        binding.movie = movie
        val application = requireNotNull(this.activity).application

        movieDatabaseDao = MovieDatabase.getInstance(application).movieDatabaseDao
        viewModelFactory = MovieDetailViewModelFactory(movie.id,movieDatabaseDao,application,movie)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MovieDetailViewModel::class.java)


        val job = GlobalScope.launch {
            movieDetailsResponse = TMDBApi.movieListRetrofitService.getDetails(movie.id)
        }
        runBlocking {
            job.join() // wait for http get request to enter strings
        }

        binding.viewmodel = viewModel
        val handler : URLhandler = URLhandler()
        val details = movieDetailsResponse // to acces movviedetailsresponse

        // below code iterates the genre objects and builds a genretext string to display in textview
        var genretext = "Genre(s): "
        for (genreObject in details.genres) {
            genretext = genretext + genreObject.genre + ", "
        }
        binding.movieGenre.text = genretext
        binding.movieHomepage.text = "Movie Homepage: " + details.homepage_url

       /** val moviedetails : DetailList = DetailList() // Måste ändra, movieDetails laddar hårdkodade värden
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
    **/
        binding.movieHomepage.setOnClickListener{
            handler.openURL(binding.root.context,details.homepage_url)
        }



        //Adding Link to IMDB
        binding.imdbUrl.setText(Html.fromHtml("https://www.imdb.com/title/" + details.imdbID))
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