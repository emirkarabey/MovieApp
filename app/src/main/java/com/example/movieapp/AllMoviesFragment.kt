package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.entity.Movie
import com.example.movieapp.viewmodel.AllMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_movies.*

@AndroidEntryPoint
class AllMoviesFragment : Fragment() {

    val viewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(AllMoviesViewModel::class.java)
    }
    private lateinit var movieAdapter:MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieAdapter = MovieAdapter()
        val recyclerView = // adapteri bağla

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.getData()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = movieAdapter
        viewModel.loadData()
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner,object :Observer<Movie>{
            override fun onChanged(t: Movie?) {
                if (t!=null){
                    movieAdapter.setList(t)
                    movieAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}