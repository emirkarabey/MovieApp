package com.emirk.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.emirk.movieapp.R
import com.emirk.movieapp.databinding.FragmentDetailsBinding
import com.emirk.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getMovieDetailsById()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe(){
        viewModel.movieDetailLiveData.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.LOADING->{

                }
                Resource.Status.SUCCESS->{
                    binding.movieDetails = it.data
                }
                Resource.Status.ERROR->{

                }
            }
        }
    }

    private fun getMovieDetailsById(){
        val args: DetailsFragmentArgs by navArgs()
        viewModel.getMovieDetailById(args.id)
    }

}