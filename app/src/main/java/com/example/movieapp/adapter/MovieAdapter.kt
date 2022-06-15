package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.entity.Movie
import kotlinx.android.synthetic.main.recycler_row.view.*

class MovieAdapter (): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    var liveData = mutableListOf<Movie>()
    fun setList(data:Movie){
        this.liveData.add(data)
    }

    class MovieViewHolder(view: View):RecyclerView.ViewHolder(view){

        val titleText:TextView = view.findViewById(R.id.row_movieName)
        val dateText:TextView = view.findViewById(R.id.row_movieDate)
        fun bindItems(movie:Movie){
            titleText.text = movie.title
            dateText.text = movie.date
        }
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
        holder.bindItems(liveData!!.get(position))
    }

    override fun getItemCount(): Int {
        if (liveData == null){
            return 0
        }else{
            return liveData!!.size
        }
    }
}