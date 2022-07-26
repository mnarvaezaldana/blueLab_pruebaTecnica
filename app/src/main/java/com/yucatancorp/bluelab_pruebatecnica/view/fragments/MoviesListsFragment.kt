package com.yucatancorp.bluelab_pruebatecnica.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.databinding.FragmentMoviesListsBinding
import com.yucatancorp.bluelab_pruebatecnica.view.MoviesAdapter
import com.yucatancorp.bluelab_pruebatecnica.viewModel.MoviesViewModel
import kotlinx.coroutines.*


/**
 * A simple [Fragment] subclass.
 * Use the [moviesListsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesListsFragment : Fragment() {

    private var _binding: FragmentMoviesListsBinding? = null
    private val binding get() = _binding!!
    private val coroutineScope = CoroutineScope(Dispatchers.Main + CoroutineName("movieCoroutine"))
    private var numberPageTopRated = 1
    private var numberPageNowPlaying = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(requireActivity())[MoviesViewModel::class.java]
        model.topRatedIds.observe(requireActivity()) { data ->
            val moviesAdapter = MoviesAdapter(data)
            val linearLayoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            moviesAdapter.setOnClickOnMovieThumbnail { id, name -> navigateToMovieDescription(id, name, model) }
            binding.rvTopRated.layoutManager = linearLayoutManager
            binding.rvTopRated.adapter = moviesAdapter
            binding.rvTopRated.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= data.size - 3) {
                        numberPageTopRated++
                        model.downloadMoreTopRatedMovies(numberPageTopRated.toString())
                    }
                }
            })
        }
        model.nowPlayingIds.observe(requireActivity()) { data ->
            val moviesAdapter = MoviesAdapter(data)
            val linearLayoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            moviesAdapter.setOnClickOnMovieThumbnail { id, name -> navigateToMovieDescription(id, name, model) }
            binding.rvNowPlaying.layoutManager = linearLayoutManager
            binding.rvNowPlaying.adapter = moviesAdapter
            binding.rvNowPlaying.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= data.size - 3) {
                        numberPageNowPlaying++
                        model.downloadMoreNowPlayingMovies(numberPageNowPlaying.toString())
                    }
                }
            })
        }
        val sharedPref = requireActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val dateLatestUpdate = sharedPref.getString(getString(R.string.date_key), "")
        val dateUpdateLabel = requireActivity().getString(R.string.last_update_label)
        binding.tvLastUpdateLabel.text = "$dateUpdateLabel $dateLatestUpdate"
    }

    private fun navigateToMovieDescription(id: Int, name: String?, model: MoviesViewModel) {
        coroutineScope.launch {
            val movie = withContext(Dispatchers.IO) { model.getMovieQuery(id) }
            findNavController().navigate(R.id.action_moviesListsFragment_to_movieDescriptionFragment, bundleOf("movie" to movie, "movieName" to name))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}