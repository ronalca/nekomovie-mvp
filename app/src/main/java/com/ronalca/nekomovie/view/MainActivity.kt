package com.ronalca.nekomovie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView

import com.ronalca.nekomovie.R
import com.ronalca.nekomovie.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), MovieRecyclerAdapter.ItemClickListener {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var movieAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMovieListRecyclerView()
    }

    private fun initMovieListRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        movieAdapter = MovieRecyclerAdapter(this@MainActivity)
        recyclerView.adapter = movieAdapter

        viewModel.movieLiveData.observe(this@MainActivity, Observer<List<String>>{ movies ->
            movieAdapter.submitList(movies)
        })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this@MainActivity, "VideoId: $position", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, DetailsActivity()::class.java)
        intent.putExtra("videoId", position)
        startActivity(intent)
    }
}