package com.yucatancorp.bluelab_pruebatecnica.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie
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

        initializeRecyclerview(requireActivity(), binding.rvTopRated, model, model.topRatedIds) { downloadMoreTopRatedMovies(model) }
        initializeRecyclerview(requireActivity(), binding.rvNowPlaying, model, model.nowPlayingIds) { downloadMoreNowPlayingMovies(model) }

        val sharedPref = requireActivity().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val dateLatestUpdate = sharedPref.getString(getString(R.string.date_key), "")
        val dateUpdateLabel = requireActivity().getString(R.string.last_update_label)
        binding.tvLastUpdateLabel.text = "$dateUpdateLabel $dateLatestUpdate"
    }

    private fun initializeRecyclerview(fragmentActivity: FragmentActivity, rv: RecyclerView, model: MoviesViewModel, mutableLiveData: MutableLiveData<ArrayList<Movie>>, onFinalItemsReached: () -> Unit) {
        val moviesAdapter = createAdapter(fragmentActivity, mutableLiveData)
        setRVProperties(fragmentActivity, rv, moviesAdapter, onFinalItemsReached)
        moviesAdapter.setOnClickOnMovieThumbnail { id, name -> navigateToMovieDescription(id, name, model) }
    }

    private fun createAdapter(fragmentActivity: FragmentActivity, mutableLiveData: MutableLiveData<ArrayList<Movie>>): MoviesAdapter {
        val moviesAdapter = MoviesAdapter()
        mutableLiveData.observe(fragmentActivity) { data ->
            moviesAdapter.addDataset(data)
        }
        return moviesAdapter
    }

    private fun setRVProperties(context: Context, rv: RecyclerView, moviesAdapter: MoviesAdapter,  onFinalItemsReached: () -> Unit) {
        rv.layoutManager = mLinearLayoutManager(context)
        rv.adapter = moviesAdapter
        setOnScrollListenerToRecyclerview(rv, moviesAdapter, onFinalItemsReached)
    }

    private fun mLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun setOnScrollListenerToRecyclerview(rv: RecyclerView, moviesAdapter: MoviesAdapter, onFinalItemsReached: () -> Unit) {
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if ((rv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() >= moviesAdapter.movies.size - 3 ) {
                    onFinalItemsReached.invoke()
                }
            }
        })
    }

    private fun downloadMoreTopRatedMovies(model: MoviesViewModel) {
        numberPageTopRated++
        model.downloadMoreTopRatedMovies(numberPageTopRated.toString())
    }

    private fun downloadMoreNowPlayingMovies(model: MoviesViewModel) {
        numberPageNowPlaying++
        model.downloadMoreNowPlayingMovies(numberPageNowPlaying.toString())
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