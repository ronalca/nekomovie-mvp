/*
 * Copyright 2022 Rony Alcala
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ronalca.nekomovie

import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieRVAdapter(val mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<MovieRVAdapter.MovieViewHolder>() {

    private var items: List<String> = listOf()

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val textView: TextView

        init {
            textView = itemView.findViewById(R.id.movie_title_textview)

            itemView.setOnClickListener {
                mItemClickListener.onItemClick(absoluteAdapterPosition)
            }
        }

        fun bind(title: String) {
            textView.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_items, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(movieList: List<String>) {
        items = movieList
        notifyDataSetChanged()
    }
}