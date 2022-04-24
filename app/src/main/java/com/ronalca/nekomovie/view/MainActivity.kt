package com.ronalca.nekomovie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

import com.ronalca.nekomovie.R
import com.ronalca.nekomovie.presenter.MainActivityPresenter
import com.ronalca.nekomovie.MainContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), MainContract.View {
    private val presenter: MainContract.Presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMovieTitles()
    }

    override fun showMovieTitles() {
        CoroutineScope(Dispatchers.IO).launch {
            val movieList: Array<String> = presenter.getMovieTitles()

            withContext(Dispatchers.Main) {
                val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
                recyclerView.adapter = MovieAdapter(movieList)
            }
        }
    }

    override fun showMovieDetails(videoId: Int) {
    }
}