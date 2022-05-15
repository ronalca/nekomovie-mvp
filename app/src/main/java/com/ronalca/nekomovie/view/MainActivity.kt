package com.ronalca.nekomovie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import kotlinx.coroutines.*

import com.ronalca.nekomovie.R
import com.ronalca.nekomovie.presenter.MainActivityPresenter
import com.ronalca.nekomovie.MainContract

class MainActivity : AppCompatActivity(), MainContract.View, MovieRecyclerAdapter.ItemClickListener {
    private val presenter = MainActivityPresenter(this@MainActivity)
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private lateinit var movieAdapter: MovieRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMovieListRecyclerView()
        showMovieTitles()
    }

    private fun initMovieListRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        movieAdapter = MovieRecyclerAdapter(this@MainActivity)

        // Create the observer which updates the UI.
        val movies = Observer<MutableList<String>> { movie ->
            recyclerView.adapter = movieAdapter
            movieAdapter.submitList(movie)
        }

        // Observe the LiveData object from the presenter and pass this activity as the LifecycleOwner and the observer.
        presenter.movieLiveData.observe(this@MainActivity, movies)
    }

    override fun showMovieTitles() {
        ioScope.launch {
            presenter.getMovieTitles()
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this@MainActivity, "VideoId: $position", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@MainActivity, DetailsActivity()::class.java)
        intent.putExtra("videoId", position)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        ioScope.cancel()
    }
}