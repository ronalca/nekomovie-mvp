package com.ronalca.nekomovie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import kotlinx.coroutines.*

import com.ronalca.nekomovie.R
import com.ronalca.nekomovie.presenter.MainActivityPresenter
import com.ronalca.nekomovie.MainContract

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter = MainActivityPresenter(this)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize the movie list RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        // Create the observer which updates the UI.
        val movies = Observer<MutableList<String>> { movie ->
            recyclerView.adapter = MovieAdapter(movie)
        }

        // Observe the LiveData object from the presenter and pass this activity as the LifecycleOwner and the observer.
        presenter.movieLiveData.observe(this, movies)

        showMovieTitles()
    }

    override fun showMovieTitles() {
        ioScope.launch {
            presenter.getMovieTitles()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ioScope.cancel()
    }
}