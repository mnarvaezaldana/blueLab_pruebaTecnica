package com.yucatancorp.bluelab_pruebatecnica.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yucatancorp.bluelab_pruebatecnica.R

class MoviesAdapter(private val ids: List<Int>): RecyclerView.Adapter<MoviesAdapter.MoviesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_thumbnail, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.bind(ids[position])
    }

    override fun getItemCount(): Int {
        return ids.size
    }

    inner class MoviesListViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var idtv: TextView = view.findViewById(R.id.tv_custom_title)

        fun bind(id: Int) {
            idtv.text = id.toString()
        }
    }
}