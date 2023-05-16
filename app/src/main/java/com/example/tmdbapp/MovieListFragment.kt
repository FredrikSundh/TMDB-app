package com.example.tmdbapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.adapter.MovieGridAdapter
import com.example.tmdbapp.adapter.MovieListAdapter
import com.example.tmdbapp.adapter.MovieListClickListener
import com.example.tmdbapp.database.MovieDatabase
import com.example.tmdbapp.database.MovieDatabaseDao
import com.example.tmdbapp.database.Movies
import com.example.tmdbapp.databinding.FragmentMovieListBinding
import com.example.tmdbapp.databinding.MovielistBinding
import com.example.tmdbapp.network.DataFetchStatus
import com.example.tmdbapp.viewmodel.MovieListViewModel
import com.example.tmdbapp.viewmodel.MovieListViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var viewModelFactory  : MovieListViewModelFactory
    private lateinit var  movieDatabaseDao : MovieDatabaseDao

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

        val application = requireNotNull(this.activity).application
        movieDatabaseDao = MovieDatabase.getInstance(application).movieDatabaseDao
        viewModelFactory = MovieListViewModelFactory(movieDatabaseDao,application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)


        var MovieGridAdapter = viewModel.movieList.value?.let {
            MovieGridAdapter(it.toMutableList(), MovieListClickListener
            { movie ->
                viewModel.onMovieListItemClicked(movie)
            })
        } // if the movielist is not null create an adapter with access to the list


        binding.movieListRv.adapter = MovieGridAdapter
        val layoutManager = GridLayoutManager(this.context, 3)

        binding.movieListRv.layoutManager = layoutManager // Sets layout to grid

        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            movieList?.let {
                MovieGridAdapter = viewModel.movieList.value?.let {MovieGridAdapter(it.toMutableList(), MovieListClickListener {
                    movie ->
                    viewModel.onMovieListItemClicked(movie)
                })}
                binding.movieListRv.adapter = MovieGridAdapter
                //MovieGridAdapter!!.submitList(it.toMutableList())
            }
        }


/**
        val movieListAdapter = MovieListAdapter(
            MovieListClickListener {
                movie ->
                viewModel.onMovieListItemClicked(movie)
            }
        )
        binding.movieListRv.adapter = movieListAdapter




        binding.movieListRv.adapter = movieListAdapter

        viewModel.movieList.observe(
            viewLifecycleOwner
        ) { movieList ->
           movieList?.let {
               movieListAdapter.submitList(movieList)
           }
        }
**/
        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner) {movie ->
            movie?.let {
                this.findNavController().navigate(MovieListFragmentDirections.actionMovieListToMovieDetails(movie))
                viewModel.onMovieDetailNavigated()
            }
        }

        viewModel.dataFetchStatus.observe(viewLifecycleOwner) {status ->
            status?.let {

                when(status) {
                    DataFetchStatus.ERROR -> {
                        binding.statusImage.visibility = View.VISIBLE
                        binding.statusImage.setImageResource(R.drawable.ic_connection_error)

                    }
                    DataFetchStatus.LOADING -> {
                        binding.statusImage.visibility = View.VISIBLE
                        binding.statusImage.setImageResource(R.drawable.loading_animation)

                    }
                    DataFetchStatus.DONE -> {
                        binding.statusImage.visibility = View.GONE
                    }
                }
            }
        }



        setHasOptionsMenu(true)
        Log.i("returning","returning root")
        return binding.root

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // may need to be changed to use menuhost and menu provider
        Log.i("hello","in optionselected")
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_load_popular_movies -> {
                Log.i("hello","popular selected")
                viewModel.getPopularFromRepo("popular")
               // viewModel.getPopularMovies()
            }
            R.id.action_load_top_rated_movies -> {
                viewModel.getPopularFromRepo("top rated")
            }
            R.id.action_load_saved_movies -> {
                viewModel.getSavedMovies()
            }
        }
        return super.onOptionsItemSelected(item)
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