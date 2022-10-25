package com.emirk.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emirk.movieapp.databinding.FragmentHomeBinding
import com.emirk.movieapp.ui.adapter.MoviesAdapter
import com.emirk.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val popularMoviesAdapter = MoviesAdapter()
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
        setupRecycler(binding.rvPopular)
        collectData()
    }

    private fun collectData(){
        lifecycleScope.launch {
            viewModel.popularMovies.data?.collectLatest {
                when(viewModel.popularMovies.status){
                    Resource.Status.LOADING->{
                        //progress bar visibility aç
                    }
                    Resource.Status.SUCCESS->{
                        //progress bar visibility kapat
                        popularMoviesAdapter.submitData(it)
                    }
                    Resource.Status.ERROR->{

                    }
                }
            }
        }
    }

    private fun setupRecycler(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = popularMoviesAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}