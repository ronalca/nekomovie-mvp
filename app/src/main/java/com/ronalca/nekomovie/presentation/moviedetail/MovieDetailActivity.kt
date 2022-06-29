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

package com.ronalca.nekomovie.presentation.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer

import kotlinx.coroutines.*
import coil.load

import com.ronalca.nekomovie.R

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private val presenter = MovieDetailPresenter(this@MovieDetailActivity)
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
            val imgUri = detail[5]
            imgView.load("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/".plus(imgUri))
        }

        presenter.detailsLiveData.observe(this@MovieDetailActivity, details)
    }

    override fun onDestroy() {
        super.onDestroy()
        ioScope.cancel()
    }
}