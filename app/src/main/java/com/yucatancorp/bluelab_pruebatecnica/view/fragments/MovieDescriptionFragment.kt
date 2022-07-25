package com.yucatancorp.bluelab_pruebatecnica.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie
import com.yucatancorp.bluelab_pruebatecnica.view.MainActivity
import com.yucatancorp.bluelab_pruebatecnica.viewModel.MoviesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDescriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDescriptionFragment : Fragment() {

    private var movieId: Int? = null
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]
        arguments?.let {
            model.getMovieQuery(it.getInt("movieId"))
            movie = model.movie
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_description, container, false)
    }

}