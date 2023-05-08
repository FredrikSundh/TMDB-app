package com.example.tmdbapp.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.MovieListFragmentDirections
import com.example.tmdbapp.databinding.MovieGridLayoutBinding
import com.example.tmdbapp.model.Movie

class MovieGridAdapter(val listItems : MutableList<Movie>, val movieClickListener : MovieListClickListener) : RecyclerView.Adapter<MovieGridAdapter.MyViewHolder>() {
    val adapterData = listItems

    public class MyViewHolder(private var binding: MovieGridLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindings(movie: Movie, movieClickListener: MovieListClickListener) {
            binding.movie = movie
            binding.clickListener = movieClickListener
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieGridLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindings(adapterData.get(position), movieClickListener)
    }

    fun submitList(newData : MutableList<Movie>) {
        adapterData.clear()
        adapterData.addAll(newData)
        this.notifyDataSetChanged()
    }



}