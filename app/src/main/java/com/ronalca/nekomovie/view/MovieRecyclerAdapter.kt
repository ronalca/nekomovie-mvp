package com.ronalca.nekomovie.view

import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
 
import com.ronalca.nekomovie.R

class MovieRecyclerAdapter(val mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    private var items: List<String> = listOf()

    interface ItemClickListener {
        fun onItemClick(position: Int)
        // fun onLongClick(position: Int)
    }

    // Describes an item view and its place within the RecyclerView
    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val textView: TextView

        init {
            // Define the click listener for the ViewHolder's View.
            textView = itemView.findViewById(R.id.movie_title_textview)

            itemView.setOnClickListener {
                mItemClickListener.onItemClick(absoluteAdapterPosition)
            }
        }

        fun bind(title: String) {
            textView.text = title
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
        return items.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(movieList: List<String>) {
        items = movieList
        notifyDataSetChanged()
    }
}