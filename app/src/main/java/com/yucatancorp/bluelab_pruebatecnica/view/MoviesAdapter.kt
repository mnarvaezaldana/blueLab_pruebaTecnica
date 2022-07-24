package com.yucatancorp.bluelab_pruebatecnica.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie

class MoviesAdapter(private val movies: List<Movie>): RecyclerView.Adapter<MoviesAdapter.MoviesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_thumbnail, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MoviesListViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var tvCustomTitle: TextView = view.findViewById(R.id.tv_custom_title)
        private var tvOriginalTitle: TextView = view.findViewById(R.id.tv_title)

        fun bind(movie: Movie) {
            tvCustomTitle.text = movie.customTitle
            if (movie.customTitle == movie.originalTitle) {
                tvOriginalTitle.visibility = View.GONE
            } else {
                tvOriginalTitle.text = movie.originalTitle
            }
        }
    }
}