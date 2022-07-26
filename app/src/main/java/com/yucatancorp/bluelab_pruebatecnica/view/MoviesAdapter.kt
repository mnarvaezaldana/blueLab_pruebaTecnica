package com.yucatancorp.bluelab_pruebatecnica.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie

typealias OnClickOnMovie = (Int, String?) -> Unit
class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesListViewHolder>() {

    private var viewGroup: ViewGroup? = null
    var onClickOnMovie: OnClickOnMovie? = null
    var movies = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        viewGroup = parent
        return MoviesListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_thumbnail, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        viewGroup?.let { holder.bind(movies[position], it) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setOnClickOnMovieThumbnail(onClickOnMovie: OnClickOnMovie) {
        this.onClickOnMovie = onClickOnMovie
    }

    fun addDataset(movies: ArrayList<Movie>) {
        for (movie in movies) {
            if (!this.movies.contains(movie)) {
                this.movies.add(movie)
            }
        }
        notifyDataSetChanged()
    }

    inner class MoviesListViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var cvMovie: CardView = view.findViewById(R.id.cv_movie)
        private var tvCustomTitle: TextView = view.findViewById(R.id.tv_custom_title)
        private var tvOriginalTitle: TextView = view.findViewById(R.id.tv_title)
        private var ivPoster: ImageView = view.findViewById(R.id.iv_movie_image)

        fun bind(movie: Movie, viewGroup: ViewGroup) {
            tvCustomTitle.text = movie.customTitle
            tvOriginalTitle.text = movie.originalTitle
            tvOriginalTitle.visibility = if (movie.customTitle == movie.originalTitle) { View.GONE } else { View.VISIBLE }
            Glide.with(viewGroup.context)
                .load(movie.posterFilmImageUrl?.getImageUrl())
                .into(ivPoster)
            cvMovie.setOnClickListener { onClickOnMovie?.invoke(movie.movieId, movie.customTitle) }
        }

        private fun String.getImageUrl(): String {
            return "https://image.tmdb.org/t/p/w500//$this"
        }
    }
}