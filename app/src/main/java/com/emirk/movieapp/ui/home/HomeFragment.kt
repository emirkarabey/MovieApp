package com.emirk.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emirk.movieapp.databinding.FragmentHomeBinding
import com.emirk.movieapp.ui.adapter.ItemClickListener
import com.emirk.movieapp.ui.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var popularMoviesAdapter = MoviesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAllRecycler()
        collectData()
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.popularMovies.data?.data?.collectLatest {
                popularMoviesAdapter.submitData(it)
            }
            viewModel.latestMovies.data?.data?.collectLatest {
                popularMoviesAdapter.submitData(it)
            }

            viewModel.topRatedMovies.data?.data?.collectLatest {
                popularMoviesAdapter.submitData(it)
            }
            viewModel.upComingMovies.data?.data?.collectLatest {
                popularMoviesAdapter.submitData(it)
            }
            viewModel.nowPlayingMovies.data?.data?.collectLatest {
                popularMoviesAdapter.submitData(it)
            }
        }
    }

    private fun setupAllRecycler() {
        setupRecycler(binding.rvPopular)
        setupRecycler(binding.rvLatest)
        setupRecycler(binding.rvTopRated)
        setupRecycler(binding.rvUpComing)
        setupRecycler(binding.rvNowPlaying)
    }


    private fun setupRecycler(recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = popularMoviesAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickMovie(id: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDetails(id)
        findNavController().navigate(action)
    }
}