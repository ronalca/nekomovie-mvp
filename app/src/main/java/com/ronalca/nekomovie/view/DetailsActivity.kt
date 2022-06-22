package com.ronalca.nekomovie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer

import com.ronalca.nekomovie.DetailsContract
import com.ronalca.nekomovie.R
import com.ronalca.nekomovie.viewmodel.DetailsActivityPresenter

import kotlinx.coroutines.*
import coil.load

class DetailsActivity : AppCompatActivity(), DetailsContract.View {
    private val presenter = DetailsActivityPresenter(this@DetailsActivity)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initDetailsPage()

        val videoId = intent.getIntExtra("videoId", 0)
        showMovieDetails(videoId)
    }

    override fun showMovieDetails(videoId: Int) {
        ioScope.launch {
            presenter.getMovieDetails(videoId)
        }
    }

    private fun initDetailsPage() {
        val imgView: ImageView = findViewById(R.id.movie_imageView)
        val textView: TextView = findViewById(R.id.TextMultiLine)

        val details = Observer<List<String>> { detail ->

            textView.text = detail.toString().plus("\n")
            val imgUri = detail[4]
            imgView.load("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/".plus(imgUri))
        }

        presenter.detailsLiveData.observe(this@DetailsActivity, details)
    }

    override fun onDestroy() {
        super.onDestroy()
        ioScope.cancel()
    }
}