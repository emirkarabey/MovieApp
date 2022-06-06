package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.entity.Movie
import kotlinx.android.synthetic.main.recycler_row.view.*

class MovieAdapter (var movieList:ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.itemView.row_movieName.text = movieList[position].movie_name.toString()
        holder.itemView.row_movieDate.text = movieList[position].movie_date.toString()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}