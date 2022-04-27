package com.ronalca.nekomovie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ronalca.nekomovie.DetailsContract
import com.ronalca.nekomovie.R
import com.ronalca.nekomovie.presenter.DetailsActivityPresenter

class DetailsActivity : AppCompatActivity(), DetailsContract.View {
    private val presenter: DetailsContract.Presenter = DetailsActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun showMovieDetails(videoId: Int) {
        TODO("Not yet implemented")
    }
}