package com.emirk.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.databinding.FragmentHomeBinding
import com.emirk.movieapp.domain.mapper.MovieEntityMapper
import com.emirk.movieapp.ui.adapter.ItemClickListener
import com.emirk.movieapp.ui.adapter.MoviesAdapter
import com.emirk.movieapp.ui.adapter.WatchLaterHomeAdapter
import com.emirk.movieapp.ui.model.MovieUiModel
import com.emirk.movieapp.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private var watchLaterAdapter = WatchLaterHomeAdapter()
    private var popularMoviesAdapter = MoviesAdapter(this)
    private var topRatedMoviesAdapter = MoviesAdapter(this)
    private var upComingMoviesAdapter = MoviesAdapter(this)
    private var nowPlayingMoviesAdapter = MoviesAdapter(this)

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
        setupWatchLaterRecycler()
        observeProgressBar()
        collectLatestData()
        getWatchLaterMovie()
    }

    private fun observeProgressBar() {
        viewModel.progressBarLiveData.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupAllRecycler() {
        setupRecycler(binding.rvPopular, popularMoviesAdapter)
        setupRecycler(binding.rvTopRated, topRatedMoviesAdapter)
        setupRecycler(binding.rvUpComing, upComingMoviesAdapter)
        setupRecycler(binding.rvNowPlaying, nowPlayingMoviesAdapter)
    }

    private fun setupWatchLaterRecycler() {
        binding.rvWatchLater.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvWatchLater.adapter = watchLaterAdapter
    }

    private fun setupRecycler(recyclerView: RecyclerView, moviesAdapter: MoviesAdapter) {
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = moviesAdapter

    }

    private fun collectLatestData() = lifecycleScope.launch {
        collectPagingData(viewModel.popularMovies, popularMoviesAdapter)
        collectPagingData(viewModel.topRatedMovies, topRatedMoviesAdapter)
        collectPagingData(viewModel.upComingMovies, upComingMoviesAdapter)
        collectPagingData(viewModel.nowPlayingMovies, nowPlayingMoviesAdapter)
    }

    private fun collectPagingData(
        data: Flow<PagingData<MovieUiModel>>,
        moviesAdapter: MoviesAdapter
    ) {
        lifecycleScope.launch {
            data.collectLatest { data ->
                moviesAdapter.submitData(data)
            }
        }
    }

    override fun onClickMovie(id: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationDetails(id)
        findNavController().navigate(action)
    }

    private fun addWatchLaterMovie(movieUi: MovieUiModel) {
        lifecycleScope.launch {
            viewModel.addWatchLaterMovie(movieUi)
        }
    }

    private fun getWatchLaterMovie() {
        viewModel.getWatchLaterMovie()
        lifecycleScope.launch {
            viewModel.movies.collect {
                when (it) {
                    is DataState.Loading -> {
                        //pb visibility
                    }
                    is DataState.Success -> {
                        //pb visibility
                        val movieUiModel =
                            MovieEntityMapper().fromEntityList(it.data as List<MovieEntity>)
                        watchLaterAdapter.movies = movieUiModel
                    }
                    is DataState.Failure -> {
                        //pb visibility
                    }
                    is DataState.Empty -> {}
                }
            }
        }
    }

    override fun onClickWatchLaterButton(movieUi: MovieUiModel) {
        addWatchLaterMovie(movieUi)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}