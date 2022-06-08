package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.entity.Movie
import com.example.movieapp.retrofit.MovieAPI
import com.example.movieapp.retrofit.MovieAPIService
import com.example.movieapp.viewmodel.AllMoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllMoviesFragment : Fragment() {


    private lateinit var viewModel: AllMoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        viewModel = ViewModelProviders.of(this).get(AllMoviesViewModel::class.java)
        //viewModel.getData()
        viewModel.searchMovieByTitle("")

    }

}