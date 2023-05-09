package com.example.tmdbapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.databinding.MovieGridLayoutBinding
import com.example.tmdbapp.databinding.ReviewsLayoutBinding
import com.example.tmdbapp.model.Movie
import com.example.tmdbapp.model.MovieReview

class MovieReviewAdapter (val listItems : MutableList<MovieReview>) : RecyclerView.Adapter<MovieReviewAdapter.reviewViewHolder>() {
    val adapterData = listItems

    public class reviewViewHolder(private var binding: ReviewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindings(MovieReview: MovieReview) {
            binding.review = MovieReview
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewAdapter.reviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ReviewsLayoutBinding.inflate(layoutInflater, parent, false)
        return MovieReviewAdapter.reviewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    override fun onBindViewHolder(holder: MovieReviewAdapter.reviewViewHolder, position: Int) {
        holder.bindings(adapterData.get(position))
    }





}
