package com.yucatancorp.bluelab_pruebatecnica.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.databinding.FragmentMoviesListsBinding
import com.yucatancorp.bluelab_pruebatecnica.viewModel.MoviesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [moviesListsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesListsFragment : Fragment() {

    private var _binding: FragmentMoviesListsBinding? = null
    private val binding get() = _binding!!

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

        binding.mButton.setOnClickListener {
            findNavController().navigate(R.id.action_moviesListsFragment_to_movieDescriptionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}