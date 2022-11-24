package com.minseongkim.android_moviesample.presentation.viewModel.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minseongkim.android_moviesample.data.model.movie.MovieResponse
import com.minseongkim.android_moviesample.databinding.MovieListBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val dummy = MovieResponse.MovieData.Movie(0, "0", 0, 0F, listOf(), "0", "0")
    private val movieList = mutableListOf<MovieResponse.MovieData.Movie>()

    init {
        for(i in 1..5) {
            movieList.add(dummy)
        }
    }

    inner class MovieViewHolder(private val binding: MovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentMovie: MovieResponse.MovieData.Movie) {
            binding.movie = currentMovie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}