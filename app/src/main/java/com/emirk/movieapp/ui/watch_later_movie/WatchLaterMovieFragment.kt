package com.emirk.movieapp.ui.watch_later_movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.emirk.movieapp.data.local.entity.MovieEntity
import com.emirk.movieapp.databinding.FragmentWatchLaterMovieBinding
import com.emirk.movieapp.domain.mapper.MovieEntityMapper
import com.emirk.movieapp.ui.adapter.WatchLaterAdapter
import com.emirk.movieapp.ui.adapter.WatchLaterClickListener
import com.emirk.movieapp.ui.model.MovieUiModel
import com.emirk.movieapp.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchLaterMovieFragment : Fragment(), WatchLaterClickListener {

    private var _binding: FragmentWatchLaterMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WatchLaterMovieViewModel by viewModels()

    private val watchLaterAdapter = WatchLaterAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchLaterMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavoriteRecycler()
        getWatchLaterMovie()
    }

    private fun getWatchLaterMovie() {
        viewModel.getWatchLaterMovie()
        lifecycleScope.launch {
            viewModel.movies.collect {
                when (it) {
                    is DataState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is DataState.Success -> {
                        val movieUiModel =
                            MovieEntityMapper().fromEntityList(it.data as List<MovieEntity>)
                        watchLaterAdapter.movies = movieUiModel

                        binding.progressBar.visibility = View.INVISIBLE
                    }
                    is DataState.Failure -> {
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                    is DataState.Empty -> {}
                }
            }
        }
    }

    private fun setupFavoriteRecycler() {
        binding.rvFavMovie.layoutManager =
            GridLayoutManager(requireContext(), 2)
        binding.rvFavMovie.adapter = watchLaterAdapter
    }

    private fun deleteWatchLaterMovie(movieId: Int) {
        viewModel.deleteWatchLaterMovie(movieId)
    }

    override fun onClickWatchLaterButton(movieUi: MovieUiModel, position: Int) {
        movieUi.id?.let { deleteWatchLaterMovie(it) }
    }
}