package com.example.tmdbapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.navigation.fragment.findNavController
import com.example.tmdbapp.adapter.MovieReviewAdapter
import com.example.tmdbapp.adapter.VideoAdapter
import com.example.tmdbapp.databinding.FragmentReviewsBinding
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.model.MovieReview
import com.example.tmdbapp.model.VideoKey
import com.example.tmdbapp.network.TMDBApi
import com.example.tmdbapp.network.getReviewResponse
import com.example.tmdbapp.network.getVideoResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewsFragment : Fragment() {

    private var _binding : FragmentReviewsBinding? = null
    private lateinit var movie: Movie

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReviewsBinding.inflate(inflater);
        movie = ReviewsFragmentArgs.fromBundle(requireArguments()).Movie
        binding.movie = movie
        var reviewList : List<MovieReview> = listOf()
        var videoKeyList : List<VideoKey> = listOf()

         val job = GlobalScope.launch {// handles http requests
             val reviews : getReviewResponse = TMDBApi.movieListRetrofitService.getReviews(movie.id) // gets reviewresponse
             reviewList = reviews.results // copies the list of all reviews into review List
             val videoKeys : getVideoResponse = TMDBApi.movieListRetrofitService.getVideos(movie.id)
             videoKeyList = videoKeys.results // stores the list of videoKeys into the variable
        }
        runBlocking {
            job.join() // wait for http get request to retrieve reviews before Creating adapter below
        }
        val MovieReviewAdapter = MovieReviewAdapter(reviewList.toMutableList()) // sets the adapter for the Reviews recyclerview
        binding.movieReviewsRv.adapter = MovieReviewAdapter

        val VideoAdapter = VideoAdapter(videoKeyList.toMutableList()) // sets the adapter for the WebViews
        binding.WebViewRV.adapter = VideoAdapter





        // Todo,Kolla api struktur på get videos använd webview i denna för att visa en youtube video. lägg till recyclerview till layout och bygg
        // video layout plus adapter plus api call för att ladda videos
        // Behöver recycler view i reviewsfragment och en layout med en webview samt en adapter och en get.



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToDetails.setOnClickListener {

            findNavController().navigate(ReviewsFragmentDirections.actionThirdFragmentToMovieDetails(movie)) // lägg till film
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}