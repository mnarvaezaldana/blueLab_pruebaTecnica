package com.yucatancorp.bluelab_pruebatecnica.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.bumptech.glide.Glide
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie
import com.yucatancorp.bluelab_pruebatecnica.databinding.FragmentMovieDescriptionBinding
import com.yucatancorp.bluelab_pruebatecnica.databinding.FragmentMoviesListsBinding
import com.yucatancorp.bluelab_pruebatecnica.view.MainActivity
import com.yucatancorp.bluelab_pruebatecnica.viewModel.MoviesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDescriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDescriptionFragment : Fragment() {

    private var _binding: FragmentMovieDescriptionBinding? = null
    private val binding get() = _binding!!
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            movie = it.getParcelable("movie")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(movie?.genericFilmImageUrl?.getImageUrl())
            .into(binding.ivMovieMainImage)
        binding.tvMovieOverview.text = movie?.overview
        binding.tvTitle.text = movie?.customTitle
    }

    private fun String.getImageUrl(): String {
        return "https://image.tmdb.org/t/p/w500//$this"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}