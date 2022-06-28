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

package com.ronalca.nekomovie.presentation.movielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ronalca.nekomovie.MovieRVAdapter

import com.ronalca.nekomovie.R
import com.ronalca.nekomovie.presentation.moviedetail.MovieDetailActivity

class MovieListActivity : AppCompatActivity(), MovieRVAdapter.ItemClickListener {
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var movieAdapter: MovieRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMovieListRecyclerView()
    }

    private fun initMovieListRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        movieAdapter = MovieRVAdapter(this@MovieListActivity)
        recyclerView.adapter = movieAdapter

        viewModel.movieLiveData.observe(this@MovieListActivity, Observer<List<String>>{ movies ->
            movieAdapter.submitList(movies)
        })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this@MovieListActivity, "VideoId: $position", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MovieListActivity, MovieDetailActivity()::class.java)
        intent.putExtra("videoId", position)
        startActivity(intent)
    }
}