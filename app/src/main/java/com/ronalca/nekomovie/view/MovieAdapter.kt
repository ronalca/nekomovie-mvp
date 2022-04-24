package com.ronalca.nekomovie.view

import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ronalca.nekomovie.R

class MovieAdapter(private val movieList: Array<String>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieTextView: TextView = itemView.findViewById(R.id.movie_title_textview)

        fun bind(word: String) {
            movieTextView.text = word
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_items, parent, false)

        return MovieViewHolder(view)
    }

    // Returns the size of data list
    override fun getItemCount(): Int {
        return movieList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }
}