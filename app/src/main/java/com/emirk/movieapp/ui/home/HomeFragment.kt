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
import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.databinding.FragmentHomeBinding
import com.emirk.movieapp.domain.mapper.MovieEntityMapper
import com.emirk.movieapp.ui.adapter.ItemClickListener
import com.emirk.movieapp.ui.adapter.MoviesAdapter
import com.emirk.movieapp.ui.adapter.WatchLaterHomeAdapter
import com.emirk.movieapp.ui.model.MovieUiModel
import com.emirk.movieapp.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private val watchLaterMoviesId = mutableListOf<Int>()

    private var watchLaterAdapter = WatchLaterHomeAdapter()
    private var popularMoviesAdapter = MoviesAdapter(this)
    private var topRatedMoviesAdapter = MoviesAdapter(this)
    private var upComingMoviesAdapter = MoviesAdapter(this)

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
        getWatchLaterMovie()
        getCollectData()
    }

    private fun getCollectData() {
        collectPopularPagingData()
        collectTopRatedPagingData()
        collectUpComingPagingData()
    }

    private fun setupAllRecycler() {
        setupRecycler(binding.rvPopular, popularMoviesAdapter)
        setupRecycler(binding.rvTopRated, topRatedMoviesAdapter)
        setupRecycler(binding.rvUpComing, upComingMoviesAdapter)
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

    private fun collectPopularPagingData() {
        viewModel.getPopularMovies()
        lifecycleScope.launch {
            viewModel.getPopularMovies.collect {
                it.collectLatest { pagingData ->
                    popularMoviesAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun collectTopRatedPagingData() {
        viewModel.getTopRatedMovies()
        lifecycleScope.launch {
            viewModel.getTopRatedMovies.collect {
                it.collectLatest { pagingData ->
                    topRatedMoviesAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun collectUpComingPagingData() {
        viewModel.getUpComingMovies()
        lifecycleScope.launch {
            viewModel.getUpComingMovies.collect {
                it.collectLatest { pagingData ->
                    upComingMoviesAdapter.submitData(pagingData)
                }
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
                    is DataState.Loading -> {}
                    is DataState.Success -> {
                        val movieUiModel =
                            MovieEntityMapper().fromEntityList(it.data as List<MovieEntity>)
                        watchLaterAdapter.movies = movieUiModel

                        watchLaterMoviesId.clear()
                        movieUiModel.forEach { movieUiModelFor ->
                            movieUiModelFor.id?.let { it1 -> watchLaterMoviesId.add(it1) }
                        }
                        popularMoviesAdapter.watchLaterMoviesId = watchLaterMoviesId
                        topRatedMoviesAdapter.watchLaterMoviesId = watchLaterMoviesId
                        upComingMoviesAdapter.watchLaterMoviesId = watchLaterMoviesId

                        if (movieUiModel.isEmpty()) {
                            binding.llWatchLater.visibility = View.GONE
                        } else {
                            binding.llWatchLater.visibility = View.VISIBLE
                        }
                    }
                    is DataState.Failure -> {}
                    is DataState.Empty -> {}
                }
            }
        }
    }

    override fun onClickWatchLaterButton(movieUi: MovieUiModel, position: Int) {
        if (movieUi.isFav == true) {
            movieUi.isFav = false
            movieUi.id?.let { deleteWatchLaterMovie(it) }
        } else {
            addWatchLaterMovie(movieUi)
            getWatchLaterMovie()
            movieUi.isFav = true
        }
    }

    private fun deleteWatchLaterMovie(movieId: Int) {
        viewModel.deleteWatchLaterMovie(movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}