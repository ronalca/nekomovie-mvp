package com.ronalca.nekomovie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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
        ioScope.launch {
            presenter.getMovieTitles()
        }
    }

    private fun initMovieListRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        movieAdapter = MovieRecyclerAdapter(this@MainActivity)
        recyclerView.adapter = movieAdapter

        /*
        // Observe the LiveData object from the presenter and pass this activity as the LifecycleOwner and the observer.
        presenter.movieLiveData.observe(this@MainActivity) { movie ->
            recyclerView.adapter = movieAdapter
        }
        */
    }

    override fun showMovieTitles(movieList: List<String>) {
        movieAdapter.submitList(movieList)
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