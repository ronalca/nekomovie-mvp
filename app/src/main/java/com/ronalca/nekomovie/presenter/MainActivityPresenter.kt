package com.ronalca.nekomovie.presenter

import com.ronalca.nekomovie.MainContract
import com.ronalca.nekomovie.model.ApiClient

class MainActivityPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val model = ApiClient()

    override suspend fun getMovieTitles(): Array<String> {
        return model.getMovieTitles()
    }

    override suspend fun getMovieDetails(videoId: Int) : Array<String> {
        return model.getMovieDetails(videoId)
    }
}