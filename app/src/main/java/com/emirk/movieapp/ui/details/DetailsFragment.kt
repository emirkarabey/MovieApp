package com.emirk.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.emirk.movieapp.R
import com.emirk.movieapp.databinding.FragmentDetailsBinding
import com.emirk.movieapp.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailsFragmentArgs by navArgs()
        getDetail(args.id)
    }

    private fun getDetail(id: Int) {
        viewModel.getMovies(id)
        lifecycleScope.launch {
            viewModel.movies.collect {
                when (it) {
                    is DataState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is DataState.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.movieDetails = it.data
                    }
                    is DataState.Failure -> {
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                    is DataState.Empty -> {}
                }
            }
        }
    }
}