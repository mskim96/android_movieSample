package com.minseongkim.android_moviesample.presentation.viewModel.movie

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minseongkim.android_moviesample.databinding.MovieLikeListBinding
import com.minseongkim.android_moviesample.databinding.MovieListBinding
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.presentation.view.movie.MovieActivity
import com.minseongkim.android_moviesample.presentation.view.movie.MovieDetailFragment

class MovieLikeAdapter() : RecyclerView.Adapter<MovieLikeAdapter.MovieViewHolder>() {

    private var movieList = listOf<Movie>()

    inner class MovieViewHolder(private val binding: MovieLikeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentMovie: Movie) {
            Glide.with(binding.root).load(currentMovie.coverImg).into(binding.imageView5)
            binding.movie = currentMovie
            binding.movieLikeContainer.setOnClickListener {
                (binding.root.context.findActivity() as MovieActivity).setFragment(
                    1,
                    data = currentMovie
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieLikeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setData(data: List<Movie>) {
        movieList = data
        notifyDataSetChanged()
    }

    fun Context.findActivity(): Context {
        while (this is ContextWrapper && this !is Activity) {
            return baseContext
        }
        return this
    }
}