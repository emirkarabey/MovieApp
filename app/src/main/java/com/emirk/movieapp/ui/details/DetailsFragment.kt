package com.emirk.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.emirk.movieapp.data.remote.util.ApiConstants
import com.emirk.movieapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getMovieDetailsById()
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()

    }

    private fun observe(){
        viewModel.movieDetailLiveData.observe(viewLifecycleOwner){
            binding.tvTitle.text = it.title
            binding.tvFirstCategory.text = it.genres.first().name
            binding.tvSecondCategory.text = it.genres.last().name
            binding.tvReleaseDate.text = it.release_date
            binding.tvOverview.text = it.overview
            binding.tvRate.text = it.popularity.toString()
            Glide.with(binding.ivBackDropImage).load(ApiConstants.getPosterPath(it.backdrop_path)).into(binding.ivBackDropImage)
            Glide.with(binding.ivPoster).load(ApiConstants.getPosterPath(it.poster_path)).into(binding.ivPoster)
        }
    }

    private fun getMovieDetailsById(){
        val args: DetailsFragmentArgs by navArgs()
        viewModel.getMovieDetailById(args.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}