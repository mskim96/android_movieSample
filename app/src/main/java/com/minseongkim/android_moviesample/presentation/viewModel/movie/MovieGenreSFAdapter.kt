package com.minseongkim.android_moviesample.presentation.viewModel.movie

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minseongkim.android_moviesample.databinding.MovieListBinding
import com.minseongkim.android_moviesample.domain.model.Movie
import com.minseongkim.android_moviesample.presentation.view.movie.MovieActivity

class MovieGenreSFAdapter() : RecyclerView.Adapter<MovieGenreSFAdapter.MovieViewHolder>() {

    private val dummy = Movie()
    private var movieList = listOf(dummy, dummy, dummy, dummy)


    inner class MovieViewHolder(private val binding: MovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentMovie: Movie) {
            Glide.with(binding.root).load(currentMovie.coverImg).override(130, 200)
                .into(binding.imageView3)
            binding.movie = currentMovie
            binding.imageView3.setOnClickListener {
                (binding.root.context.findActivity() as MovieActivity).setFragment(1, data = currentMovie)
            }
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