package com.ronalca.nekomovie.presenter

import com.ronalca.nekomovie.DetailsContract
import com.ronalca.nekomovie.model.ApiClient

class DetailsActivityPresenter(private val view: DetailsContract.View): DetailsContract.Presenter {
    private val model = ApiClient()

    override suspend fun getMovieDetails(videoId: Int) : MutableList<String> {
        return model.getMovieDetails(videoId)
    }
}